package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.service.BidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/bid")
public class BidController {

    private final BidService bidService;
}
