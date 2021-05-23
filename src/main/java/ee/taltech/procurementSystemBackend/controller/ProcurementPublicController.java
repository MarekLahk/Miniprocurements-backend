package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementPublicDto;
import ee.taltech.procurementSystemBackend.service.ProcurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/public/procurement")
@AllArgsConstructor
public class ProcurementPublicController {

    private final ProcurementService procurementService;

    @GetMapping("{partnerUuid}")
    public ProcurementPublicDto getProcurementInfo(@PathVariable UUID partnerUuid) {
        return procurementService.getProcurementInfo(partnerUuid);
    }

}
