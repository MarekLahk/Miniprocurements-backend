package ee.taltech.procurementSystemBackend.repository;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BidRepository  extends RepositoryInterface<Bid> {

    Optional<Bid> findFirstByLinkIdAndBidStatus(UUID bidderLinkId, Integer status);

    Optional<Bid> findByIdAndLinkId(Integer id, UUID bidderLinkId);
}

