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
        if (!bidRepository.findByBidderLinkIdAndBidStatus(
                bidderLinkId, 1).isEmpty()) {
            throw new BidException("There can be only one waiting bid per procurement partner.");
        }
    }

    public Bid getBidToUpdate(UUID bidderLinkId) {
        List<Bid> bidToUpdate = bidRepository.findByBidderLinkIdAndBidStatus(
                bidderLinkId, 1);
        if (bidToUpdate.isEmpty()) {
            throw new BidException("No bid to update");
        }
        return bidToUpdate.get(0);
    }

    public void checkBidBeforeSetToActive(Bid bid) {

        if (bid.getBidValue() == null) {
            throw new BidException("Bid value cannot be null when setting to active");
        }
        // TODO: 4/26/2021 document or description must be provided
        if (bid.getDescription() == null) {
            throw new BidException("Bid description cannot be null when setting to active");
        }
    }
}
