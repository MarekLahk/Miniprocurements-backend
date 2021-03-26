package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

}
