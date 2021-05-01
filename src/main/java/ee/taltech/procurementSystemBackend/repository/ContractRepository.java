package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Contract;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends RepositoryInterface<Contract> {

    Optional<Contract> findByContractIdAndAddedBy(Integer contractId, Integer addedBy);

    Integer countByContractId(Integer contractId);
}
