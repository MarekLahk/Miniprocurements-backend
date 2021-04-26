package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BidUtils {

    private final BidRepository bidRepository;

    public void checkBidBeforeAdding(UUID bidderLinkId) {
        if (bidRepository.findFirstByBidderLinkIdAndBidStatus(
                bidderLinkId, 1).isPresent()) {
            throw new BidException("There can be maximally one waiting bid per procurement partner.");
        }
    }

    public Bid getBidToUpdate(UUID bidderLinkId) {
        return bidRepository.findFirstByBidderLinkIdAndBidStatus(
                bidderLinkId, 1)
                .orElseThrow(() -> new BidException("No bid to update"));
    }

    public void checkBidBeforeSetToActive(Bid bid, UUID bidderLinkId) {
        if (bidRepository.findFirstByBidderLinkIdAndBidStatus(
                bidderLinkId, 2).isPresent()) {
            throw new BidException("There can be maximally one active bid per procurement partner.");
        }
        if (bid.getBidValue() == null) {
            throw new BidException("Bid value cannot be null when setting to active");
        }
        if (bid.getBidValue() < 0) {
            throw new BidException("Bid value must be higher than 0 when setting to active");
        }
        // TODO: 4/26/2021 document or description must be provided
        if (bid.getDescription() == null) {
            throw new BidException("Bid description cannot be null when setting to active");
        }
        if (bid.getDescription().isBlank()) {
            throw new BidException("Bid description cannot be blank when setting to active");
        }
    }

    public void checkBidBeforeSetToWaiting(Bid bid, UUID bidderLinkId) {
        if (bidRepository.findFirstByBidderLinkIdAndBidStatus(
                bidderLinkId, 1).isPresent()) {
            throw new BidException("There can be only one active bid per procurement partner.");
        }
    }
}
