package ee.taltech.procurementSystemBackend.repository;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BidRepository  extends RepositoryInterface<Bid> {

    Optional<Bid> findFirstByBidderLinkIdAndBidStatus(UUID bidderLinkId, Integer status);

    Optional<Bid> findByBidIdAndBidderLinkId(Integer id, UUID bidderLinkId);
}

