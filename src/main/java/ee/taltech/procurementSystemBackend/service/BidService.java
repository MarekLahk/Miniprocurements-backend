package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import ee.taltech.procurementSystemBackend.utils.BidUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class BidService extends ServiceBase<Bid, BidDto> {

    private final BidRepository bidRepository;
    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;
    private final BidUtils bidUtils;

    public BidService(BidRepository bidRepository,
                      MiniprocurementPartnerRepository miniprocurementPartnerRepository, BidUtils bidUtils) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
        this.bidUtils = bidUtils;
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
        bid.setTimeOfRegister(new Timestamp(System.currentTimeMillis()));

        return toDtoOptional(bidRepository.save(bid)).get();
    }

    public BidDto updateBid(UUID partnerUuid, BidDto bidDto) {
        Bid sourceBid = bidUtils.getBidToUpdate(partnerUuid);
        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));
        bid.setBidId(sourceBid.getBidId());
        bid.setBidderLinkId(partnerUuid);
        bid.setBidStatus(1);
        return toDtoOptional(bidRepository.save(bid)).get();
    }

    public void deleteBidById(Integer id) {
        bidRepository.deleteById(id);
    }

    public BidDto patchBidStatus(UUID partnerUuid, BidDto bidDto) {
        return null;
    }
}

