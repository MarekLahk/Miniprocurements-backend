package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiniprocurementRepository extends RepositoryInterface<Miniprocurement> {

    Optional<Miniprocurement> findByProcurementIdAndAddedBy(Integer procurementId, Integer addedBy);

    Integer countByContractId(Integer contractId);
}
