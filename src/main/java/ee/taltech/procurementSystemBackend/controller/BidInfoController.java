package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.BidResponseDto;
import ee.taltech.procurementSystemBackend.service.BidService;
import ee.taltech.procurementSystemBackend.service.ProcurementPartnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/public/bidInfo")
@AllArgsConstructor
public class BidInfoController {

    private final ProcurementPartnerService procurementPartnerService;
    private final BidService bidService;

    @GetMapping("{partnerUuid}")
    public BidResponseDto getBidInfo(@PathVariable UUID partnerUuid) {
        return bidService.getBidInfo(partnerUuid);
    }

}
