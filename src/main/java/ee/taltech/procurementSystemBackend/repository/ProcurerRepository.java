package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Procurer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcurerRepository extends RepositoryInterface<Procurer> {

    Optional<Procurer> findByProcurementIdAndAndEmployeeId(Integer procurementId, Integer employeeId);
}
