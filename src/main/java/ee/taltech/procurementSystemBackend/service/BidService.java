package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.Dto.*;
import ee.taltech.procurementSystemBackend.models.mapper.BidMapper;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.*;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import ee.taltech.procurementSystemBackend.utils.BidResponseUtils;
import ee.taltech.procurementSystemBackend.utils.BidUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BidService extends ServiceBase<Bid, BidDto> {

    private final BidRepository bidRepository;
    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;
    private final BidUtils bidUtils;
    private final BidResponseUtils bidResponseUtils;
    private final MiniprocurementMapper procurementMapper;

    public BidService(BidRepository bidRepository,
                      MiniprocurementPartnerRepository miniprocurementPartnerRepository,
                      BidUtils bidUtils, BidResponseUtils bidResponseUtils) {
        super(bidRepository, BidMapper.INSTANCE);
        this.bidRepository = bidRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
        this.bidUtils = bidUtils;
        this.bidResponseUtils = bidResponseUtils;
        this.procurementMapper = MiniprocurementMapper.INSTANCE;
    }

    public BiddingResponse getCurrentWaitingBid(UUID partnerUuid) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(partnerUuid)
                .orElseThrow(() -> new BidException("No such procurement partner"));

        Miniprocurement procurementModel = partner.getMiniprocurement();
        MiniProcurementDto procurement = procurementMapper.toDto(
                procurementModel
        );

        List<QuestionAndRepliesResponse> questionReplyMap =
                bidResponseUtils.getQuestionsAndReplies(partner.getMiniprocurementPartnerProcurementId());

        BiddingResponse biddingResponse = new BiddingResponse();
        biddingResponse.setProcurement(procurement);
        biddingResponse.setQuestionsAndRelies(questionReplyMap);

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

        if (partner.getMiniprocurement().getStatus() != 2) {
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

