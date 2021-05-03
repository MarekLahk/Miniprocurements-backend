package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.service.BidService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/public/bids")
@AllArgsConstructor
public class BidPublicController {

    private final BidService bidService;

    @GetMapping("{partnerUuid}")
    public Optional<Bid> getCurrentWaitingBid(@PathVariable UUID partnerUuid) {
        return bidService.getCurrentWaitingBit(partnerUuid);
    }

    @PostMapping("{partnerUuid}")
    public ResponseEntity<BidDto> addBid(@PathVariable UUID partnerUuid,
                                         @RequestBody BidDto bidDto) {
        BidDto dto = bidService.addBid(partnerUuid, bidDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
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
}
