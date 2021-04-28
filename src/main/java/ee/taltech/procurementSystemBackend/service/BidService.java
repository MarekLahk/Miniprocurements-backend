package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
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
    private final BidUtils bidUtils;

    public BidService(BidRepository bidRepository,
                      MiniprocurementPartnerRepository miniprocurementPartnerRepository,
                      BidUtils bidUtils) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
        this.bidUtils = bidUtils;
    }

    public List<BidDto> getCurrentWaitingBid(UUID partnerUuid) {
        Optional<Bid> bid = bidRepository.findFirstByBidderLinkIdAndBidStatus(partnerUuid, 1);
        if (bid.isPresent()) {
            BidDto dto = toDtoOptional(bid.get()).get();
            return List.of(dto);
        }
        return List.of();
    }

    public BidDto addBid(UUID partnerUuid, BidDto bidDto) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("Invalid uuid"));

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

