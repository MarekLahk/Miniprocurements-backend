package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.Dto.BidInfoDto;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurementPartnerRepository;
import ee.taltech.procurementSystemBackend.utils.BidUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BidService extends ServiceBase<Bid, BidDto> {

    private final BidRepository bidRepository;
    private final ProcurementPartnerRepository procurementPartnerRepository;
    private final BidUtils bidUtils;
    private final ProcurementMapper procurementMapper;

    public BidService(BidRepository bidRepository,
                      ProcurementPartnerRepository procurementPartnerRepository,
                      BidUtils bidUtils) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.procurementPartnerRepository = procurementPartnerRepository;
        this.bidUtils = bidUtils;
        this.procurementMapper = ProcurementMapper.INSTANCE;
    }

    public BidInfoDto getBidInfo(UUID partnerUUID) {
        ProcurementPartner procurementPartner = procurementPartnerRepository.findByLinkId(partnerUUID)
                .orElseThrow(() -> new BidException("No such bid"));
        System.out.println(procurementPartner.getProcurement().getCreatedAt());
        System.out.println(procurementPartner.getProcurement().getUpdatedAt());
        return procurementMapper.toInfoDto(procurementPartner.getProcurement());

    }

    public Optional<Bid> getCurrentWaitingBit(UUID partnerUUID) {
        return bidRepository.findFirstByLinkIdAndBidStatus(partnerUUID, 1);
    }

//    public BidInfoDto getCurrentWaitingBid(UUID partnerUuid) {
//        ProcurementPartner partner = procurementPartnerRepository
//                .findByLinkId(partnerUuid)
//                .orElseThrow(() -> new BidException("No such procurement partner"));
//
//        Procurement procurementModel = partner.getProcurement();
//        ProcurementDto procurement = procurementMapper.toDto(
//                procurementModel
//        );
//
//        List<QuestionAndRepliesResponse> questionReplyMap =
//                bidResponseUtils.getQuestionsAndReplies(partner.getProcurementId());
//
//        BidInfoDto bidResponseDto = new BidInfoDto();
//        bidResponseDto.setProcurement(procurement);
//        bidResponseDto.setQuestionsAndRelies(questionReplyMap);
//
//        Optional<Bid> bid = bidRepository.findFirstByLinkIdAndBidStatus(partnerUuid, 1);
//        if (bid.isPresent()) {
//            BidDto dto = toDtoOptional(bid.get()).get();
//            bidResponseDto.setBid(List.of(dto));
//        } else {
//            bidResponseDto.setBid(List.of());
//        }
//        return bidResponseDto;
//    }

    public BidDto addBid(UUID partnerUuid, BidDto bidDto) {
        ProcurementPartner procurementPartner = procurementPartnerRepository
                .findByLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("Invalid uuid"));

        if (procurementPartner.getProcurement().getStatus() != 2) {
            throw new BidException("Cannot add bid to non active procurement");
        }

        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));
        bidUtils.checkBidBeforeAdding(partnerUuid);
        bid.setLinkId(partnerUuid);
        bid.setProcurementPartnerId(procurementPartner.getPartnerId());
        bid.setBidStatus(1);
        bid.setProcurementId(procurementPartner.getProcurementId());
        return toDtoOptional(bidRepository.save(bid)).get();
    }

    public BidDto updateBid(UUID partnerUuid, BidDto bidDto) {
        Bid bid = toModelOptional(bidDto)
                .orElseThrow(() -> new BidException("No bid provided"));

        Bid sourceBid = bidUtils.getBidToUpdate(partnerUuid, bidDto);
        bidUtils.checkIfBidIsInactive(sourceBid);
        bidUtils.checkIfBidIsActive(sourceBid);

        bid.setId(sourceBid.getId());
        bid.setProcurementId(sourceBid.getProcurementId());
        bid.setLinkId(partnerUuid);
        bid.setBidStatus(sourceBid.getBidStatus());
        bid.setCreatedAt(null);

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
        sourceBid.setCreatedAt(null);
        return toDtoOptional(bidRepository.save(sourceBid)).get();

    }

    public List<BidDto> getPartnerBids(UUID partnerUUID) {
        ProcurementPartner procurementPartner = procurementPartnerRepository.findByLinkId(partnerUUID)
                .orElseThrow(() -> new BidException("Invalid UUID"));
        System.out.println(procurementPartner.getLinkId());
        System.out.println(procurementPartner.toString());
        List<Bid> bids1 = procurementPartner.getBids();
        System.out.println(bids1);
        List<BidDto> bids = toDtoList(bids1);
        return bids;

    }
}

