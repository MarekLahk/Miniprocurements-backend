package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.Dto.BiddingResponse;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.utils.BidUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BidService extends ServiceBase<Bid, BidDto> {

    private final BidRepository bidRepository;
    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;
    private final MiniprocurementRepository miniprocurementRepository;
    private final BidUtils bidUtils;
    private final MiniprocurementMapper procurementMapper;

    public BidService(BidRepository bidRepository,
                      MiniprocurementPartnerRepository miniprocurementPartnerRepository,
                      MiniprocurementRepository miniprocurementRepository, BidUtils bidUtils) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
        this.miniprocurementRepository = miniprocurementRepository;
        this.bidUtils = bidUtils;
        this.procurementMapper = MiniprocurementMapper.INSTANCE;
    }

    public BiddingResponse getCurrentWaitingBid(UUID partnerUuid) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("No such procurement partner"));
        MiniProcurementDto procurement = procurementMapper.toDto(
                miniprocurementRepository.findById(partner.getMiniprocurementPartnerProcurementId()).get()
        );
        BiddingResponse biddingResponse = new BiddingResponse();
        biddingResponse.setProcurement(procurement);
        Optional<Bid> bid = bidRepository.findFirstByBidderLinkIdAndBidStatus(partnerUuid, 1);
        if (bid.isPresent()) {
            BidDto dto = toDtoOptional(bid.get()).get();
            biddingResponse.setBid(List.of(dto));
        } else {
            biddingResponse.setBid(List.of());
        }
        return biddingResponse;
    }

    public BidDto addBid(UUID partnerUuid, BidDto bidDto) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("Invalid uuid"));

        if (miniprocurementRepository.findById(
                partner.getMiniprocurementPartnerProcurementId()).get().getStatus() != 2) {
            throw new BidException("Cannot add bid to non active procurement");
        }

        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));
        bidUtils.checkBidBeforeAdding(partnerUuid);
        bid.setBidderLinkId(partnerUuid);
        bid.setBidStatus(1);
        bid.setProcurementId(partner.getMiniprocurementPartnerProcurementId());
        return toDtoOptional(bidRepository.save(bid)).get();
    }

    public BidDto updateBid(UUID partnerUuid, BidDto bidDto) {
        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));

        Bid sourceBid = bidUtils.getBidToUpdate(partnerUuid, bidDto);
        bidUtils.checkIfBidIsInactive(sourceBid);
        bidUtils.checkIfBidIsActive(sourceBid);

        bid.setBidId(sourceBid.getBidId());
        bid.setProcurementId(sourceBid.getProcurementId());
        bid.setBidderLinkId(partnerUuid);
        bid.setBidStatus(sourceBid.getBidStatus());
        bid.setTimeOfRegister(sourceBid.getTimeOfRegister());

        return toDtoOptional(bidRepository.save(bid)).get();
    }

    public BidDto patchBidStatus(UUID partnerUuid, BidDto bidDto) {
        Bid sourceBid = bidUtils.getBidToUpdate(partnerUuid, bidDto);
        bidUtils.checkIfBidIsInactive(sourceBid);
        bidUtils.checkBidBeforeSetToActive(sourceBid);

        Optional<Bid> currentActiveOptional = bidUtils.getCurrentActiveBid(partnerUuid);
        if (currentActiveOptional.isPresent()) {
            Bid currentActive = currentActiveOptional.get();
            currentActive.setBidStatus(3);
            bidRepository.save(currentActive);
        }

        sourceBid.setBidStatus(2);
        sourceBid.setTimeOfRegister(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(bidRepository.save(sourceBid)).get();

    }
}

