package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.AuthException;
import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.ProcurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProcurementUtils {

    private final ProcurerRepository procurerRepository;
    private final ProcurementRepository procurementRepository;
    private final AuthUtils authUtils;

    public void checkProcurementDeadlineIsNotInPast(Timestamp timestamp) {
        if (timestamp.before(new Timestamp(System.currentTimeMillis()))) {
            throw new ProcurementException("Deadline cannot be before now.");
        }
    }

    public Person checkEmployeePermissionAndProcurementPresence(Integer procurementId, Authentication authentication) {

        Person person = authUtils.getPersonToPerformOperations(authentication);
        Optional<Procurer> procurer = procurerRepository.findByProcurementIdAndAndEmployeeId(
                procurementId,
                person.getId());

        if (procurer.isEmpty()) {
            throw new AuthException("This person does not have permission to update this procurement");
        }
        return person;
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

    public void saveProcurerForAddedProcurement(Integer procurementId, Integer creatorId) {
        Procurer procurer = Procurer.builder()
                .procurementId(procurementId)
                .employeeId(creatorId).build();
        procurer.setCreatedAt(LocalDateTime.now());
        procurer.setUpdatedAt(LocalDateTime.now());

        procurerRepository.save(
                procurer
        );
    }

    public Procurement getProcurement(Integer id) {
        return procurementRepository.findById(id).orElseThrow(() -> new RequestedObjectNotFoundException(
                String.format("Procurement with id [%d] does not exist", id)));
    }
}
