package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.AuthException;
import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProcurementUtils {

    private final MiniprocurementRepository miniprocurementRepository;

    public void checkProcurementDeadlineIsNotInPast(Timestamp timestamp) {
        if (timestamp.before(new Timestamp(System.currentTimeMillis()))) {
            throw new MiniprocurementException("Deadline cannot be before now.");
        }
    }

    public void checkEmployeePermissionAndProcurementPresence(Integer procurementId,
                                                              Integer personId,
                                                              Optional<Miniprocurement> optionalProcurement) {
        if (miniprocurementRepository.findByProcurementIdAndAddedBy(procurementId, personId).isEmpty()) {
            throw new AuthException("This person does not have permission to update this procurement");
        }

        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", procurementId));
        }
    }

    public void checkProcurementBeforeStatusPatch(Miniprocurement procurement) {
        if (procurement.getDescription() == null) {
            throw new MiniprocurementException("Description must be present on status patch");
        }
        if (procurement.getDescription() != null && procurement.getDescription().length() == 0) {
            throw new MiniprocurementException("Description must not be blank");
        }
        if (procurement.getRequirements() == null) {
            throw new MiniprocurementException("Requirements must be present on status patch");
        }
        if (procurement.getRequirements() != null && procurement.getRequirements().length() == 0) {
            throw new MiniprocurementException("Requirements must not be blank");
        }
        if (procurement.getDeadline() == null) {
            throw new MiniprocurementException("Deadline must be present on status patch");
        }
    }

}
