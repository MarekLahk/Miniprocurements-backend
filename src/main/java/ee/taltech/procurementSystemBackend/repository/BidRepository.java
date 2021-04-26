package ee.taltech.procurementSystemBackend.repository;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BidRepository  extends RepositoryInterface<Bid> {

    List<Bid> findByBidderLinkIdAndBidStatus(UUID bidderLinkId, Integer status);
}

