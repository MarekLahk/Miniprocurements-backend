package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.service.BidService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/public/bids")
@AllArgsConstructor
public class BidPublicController {

    private final BidService bidService;

    @GetMapping("{partnerUuid}")
    public List<BidDto> getCurrentWaitingBid(@PathVariable UUID partnerUuid) {
        return bidService.getCurrentWaitingBid(partnerUuid);
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
}
