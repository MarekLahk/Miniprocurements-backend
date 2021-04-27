package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.search.BidSearch;
import ee.taltech.procurementSystemBackend.service.BidService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/bids")
public class BidController extends ControllerBase<Bid, BidDto, BidSearch>{

    private final BidService bidService;

    public BidController(BidService bidService) {
        super(bidService, Bid.class, BidDto.class);
        this.bidService = bidService;
    }

    @PostMapping("{partnerUuid}")
    public BidDto addBid(@PathVariable UUID partnerUuid,
                         @RequestBody BidDto bidDto) {
        return bidService.addBid(partnerUuid, bidDto);
    }

    /**
     * bidId must be provided in dto.
     */
    @PutMapping("{partnerUuid}")
    public BidDto updateBid(@PathVariable UUID partnerUuid,
                            @RequestBody BidDto bidDto) {
        return bidService.updateBid(partnerUuid, bidDto);
    }

    /**
     * Endpoint to patch bid status.
     * bidId and bidStatus must be provided in dto.
     */
    @PatchMapping("{partnerUuid}")
    public BidDto patchBidStatus(@PathVariable UUID partnerUuid,
                                 @RequestBody BidDto bidDto) {
        return bidService.patchBidStatus(partnerUuid, bidDto);
    }

    @Deprecated
    @DeleteMapping("delete/{id}")
    public void deleteBidById(@PathVariable Integer id) {
        bidService.deleteBidById(id);
    }

}

