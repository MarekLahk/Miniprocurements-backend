package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiniprocurementRepository extends JpaRepository<Miniprocurement, Integer> {

    List<Miniprocurement> findMiniprocurementByAddedBy(Integer addedBy);
}
