package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class BidService extends ServiceBase<Bid, BidDto> {

    private final BidRepository bidRepository;
    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;

    public BidService(BidRepository bidRepository,
                      MiniprocurementPartnerRepository miniprocurementPartnerRepository) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
    }

    public BidDto addBid(BidDto bidDto, UUID partnerUuid) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("Invalid uuid"));

        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));

        bid.setBidder(partner.getMiniprocurementPartnerPartnerId());
        bid.setBidStatus(1);
        bid.setTimeOfRegister(new Timestamp(System.currentTimeMillis()));

        return toDtoOptional(bidRepository.save(bid)).get();
    }

//    public BidDto updateBid(Integer id, BidDto bidDto) {
//        if (bidRepository.findById(id).isEmpty()) {
//            throw new RequestedObjectNotFoundException(
//                    String.format("Bid with id [%d] does not exist", id));
//        }
//        Bid bid = toModelOptional(bidDto)
//                .orElseThrow(()-> new BidException(
//                        String.format("Bid with id [%d] was not found", id)
//                ));
//        bid.setBidId(id);
//        return toDtoOptional(bidRepository.save(bid)).orElseThrow(
//                ()->new BidException("Bid was not saved")
//        );
//    }

    public void deleteBidById(Integer id) {
        bidRepository.deleteById(id);
    }
}

