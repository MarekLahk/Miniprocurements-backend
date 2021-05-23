package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BidUtils {

    private final BidRepository bidRepository;

    public void checkBidBeforeAdding(UUID bidderLinkId) {
        if (bidRepository.findFirstByLinkIdAndStatus(
                bidderLinkId, 1).isPresent()) {
            throw new BidException("There can be maximally one waiting bid per procurement partner.");
        }
    }

    public Bid getBidToUpdate(UUID bidderLinkId, BidDto dto) {
        Integer bidId = Optional.ofNullable(dto.getId())
                .orElseThrow(() -> new BidException("No bid id provided in dto"));
        return bidRepository.findByIdAndLinkId(
                bidId, bidderLinkId)
                .orElseThrow(() -> new BidException("No bid to update"));
    }

    public void checkBidBeforeSetToActive(Bid bid) {
        if (bid.getStatus() == 2) {
            throw new BidException("Bid is already active");
        }
        if (bid.getValue() == null) {
            throw new BidException("Bid value cannot be null when setting to active");
        }
        if (bid.getValue() <= 0) {
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

    public Optional<Bid> getCurrentActiveBid(UUID bidderLinkId) {
        return bidRepository.findFirstByLinkIdAndStatus(
                bidderLinkId, 2);
    }

    public void checkIfBidIsInactive(Bid bid) {
        if (bid.getStatus() == 3) {
            throw new BidException("Inactive bid cannot be updated");
        }
    }

    public void checkIfBidIsActive(Bid bid) {
        if (bid.getStatus() == 2) {
            throw new BidException("Active bid cannot be updated");
        }
    }
}
