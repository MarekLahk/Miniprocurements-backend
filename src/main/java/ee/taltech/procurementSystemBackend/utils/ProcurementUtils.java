package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.AuthException;
import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProcurementUtils {

    private final PocurementRepository pocurementRepository;

    public void checkProcurementDeadlineIsNotInPast(Timestamp timestamp) {
        if (timestamp.before(new Timestamp(System.currentTimeMillis()))) {
            throw new ProcurementException("Deadline cannot be before now.");
        }
    }

    public void checkEmployeePermissionAndProcurementPresence(Integer procurementId,
                                                              Integer personId,
                                                              Optional<Procurement> optionalProcurement) {
        if (pocurementRepository.findByIdAndCreatedById(procurementId, personId).isEmpty()) {
            throw new AuthException("This person does not have permission to update this procurement");
        }

        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", procurementId));
        }
    }

    public void checkProcurementBeforeStatusPatch(Procurement procurement) {
        if (procurement.getDescription() == null) {
            throw new ProcurementException("Description must be present on status patch");
        }
        if (procurement.getDescription() != null && procurement.getDescription().length() == 0) {
            throw new ProcurementException("Description must not be blank");
        }
        if (procurement.getRequirements() == null) {
            throw new ProcurementException("Requirements must be present on status patch");
        }
        if (procurement.getRequirements() != null && procurement.getRequirements().length() == 0) {
            throw new ProcurementException("Requirements must not be blank");
        }
        if (procurement.getDeadline() == null) {
            throw new ProcurementException("Deadline must be present on status patch");
        }
    }

    public void checkThatProcurementCanBePatchedOrUpdated(Procurement procurement) {
        if (procurement.getStatus() == 3) {
            throw new ProcurementException("Finished procurement cannot be patched or updated");
        }
        if (procurement.getStatus() == 4) {
            throw new ProcurementException("Deleted procurement cannot be patched or updated");
        }
    }
}
