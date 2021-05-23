package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Procurement;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcurementRepository extends RepositoryInterface<Procurement> {

    Optional<Procurement> findByIdAndCreatedById(Integer procurementId, Integer addedBy);

    Integer countByContractId(Integer contractId);
}
