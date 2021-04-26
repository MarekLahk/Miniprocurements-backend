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

    @PostMapping
    public BidDto addBid(@RequestBody BidDto bidDto,
                         @RequestParam UUID partnerUuid) {
        return bidService.addBid(bidDto, partnerUuid);
    }

//    @PutMapping("{id}")
//    public BidDto updateBid(@PathVariable Integer id,
//                            @RequestBody BidDto bidDto) {
//        return bidService.updateBid(id, bidDto);
//    }

    @Deprecated
    @DeleteMapping("delete/{id}")
    public void deleteBidById(@PathVariable Integer id) {
        bidService.deleteBidById(id);
    }

}

