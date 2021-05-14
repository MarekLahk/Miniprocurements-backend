package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProcurementService extends ServiceBase<Procurement, ProcurementDto> {

    private final PocurementRepository pocurementRepository;
    private final AuthUtils authUtils;
    private final ProcurementUtils procurementUtils;

    public ProcurementService(RepositoryInterface<Procurement> repository,
                              PocurementRepository pocurementRepository,
                              AuthUtils authUtils,
                              ProcurementUtils procurementUtils) {
        super(repository, ProcurementMapper.INSTANCE);
        this.pocurementRepository = pocurementRepository;
        this.authUtils = authUtils;
        this.procurementUtils = procurementUtils;
    }

    public ProcurementDto addProcurement(ProcurementDto dto, Authentication authentication) {
        Procurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementException("No procurement dto provided"));

        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        procurement.setCreatedById(creatorId);
        procurement.setStatus((short) 1);
        procurement.setCreatedAt(null);


        Procurement savedProcurement = pocurementRepository.save(procurement);

        procurementUtils.saveProcurerForAddedProcurement(savedProcurement.getId(), creatorId);

        return toDtoOptional(savedProcurement).get();
    }

    public ProcurementDto updateProcurement(Integer id,
                                            ProcurementDto dto,
                                            Authentication authentication) {
        Optional<Procurement> optionalProcurement = pocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getId(), optionalProcurement);

        // optional isPresent is checked in utils
        Integer addedBy = optionalProcurement.get().getCreatedById();

        // get this to take some values that cannot be passed by put method
        Procurement initialProcurement = optionalProcurement.get();
        procurementUtils.checkThatProcurementCanBePatchedOrUpdated(initialProcurement);

        Procurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementException("No procurement dto provided"));

        procurement.setId(id);
        procurement.setCreatedById(person.getId());
        procurement.setCreatedAt(initialProcurement.getCreatedAt());
        procurement.setUpdatedAt(LocalDateTime.now());
        procurement.setCreatedById(addedBy);
        boolean hasContract = dto.getContractId() != null;
        procurement.setHasContract(dto.getContractId() != null);
        if (hasContract) {
            Integer contractSubId = pocurementRepository.countByContractId(dto.getContractId()) + 1;
            procurement.setContractSubId(contractSubId);
        }

        procurement.setStatus(initialProcurement.getStatus());

        if (initialProcurement.getStatus() == 2) {
            // if this is active procurement then check needed attributes
            procurementUtils.checkProcurementBeforeStatusPatch(procurement);
            procurementUtils.checkProcurementDeadlineIsNotInPast(procurement.getDeadline());
            System.out.println("Email must be sent");
            // TODO: 5/5/2021 send emails claiming that active procurement was updated
        }

        return toDtoOptional(pocurementRepository.save(procurement))
                .orElseThrow(() -> new ProcurementException("Could not update procurement"));
    }

    public ProcurementDto patchProcurementStatus(Integer id, ProcurementDto dto, Authentication authentication) {
        Optional<Procurement> optionalProcurement = pocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getId(), optionalProcurement);
        if (dto.getStatus() == null) throw new ProcurementException("Provided status is not present");
        if (!(dto.getStatus() == 2 || dto.getStatus() == 4)) throw new ProcurementException("Status to patch must be equal to 2 or 4");
        // optional isPresent is checked in utils
        Procurement procurement = optionalProcurement.get();
        procurementUtils.checkThatProcurementCanBePatchedOrUpdated(procurement);

        Short newStatus = dto.getStatus();
        if (procurement.getStatus() == 1 && newStatus == 2) {
            boolean hasContract = dto.getContractId() != null;
            procurement.setHasContract(dto.getContractId() != null);
            if (hasContract) {
                Integer contractSubId = pocurementRepository.countByContractId(dto.getContractId()) + 1;
                procurement.setContractSubId(contractSubId);
            }
            System.out.println("Procurement was activated");
            // TODO: 5/5/2021 Send emails that procurement was activated
        }
        if (procurement.getStatus() == 2 && newStatus == 4) {
            System.out.println("Procurement was deleted");
            // TODO: 5/5/2021 Send emails that active procurement was deleted
        }
        procurement.setStatus(newStatus);
        if (newStatus == 2) {
            procurement.setCreatedAt(LocalDateTime.now());
            procurementUtils.checkProcurementBeforeStatusPatch(procurement);
            procurementUtils.checkProcurementDeadlineIsNotInPast(procurement.getDeadline());
        }
        return toDtoOptional(pocurementRepository.save(procurement)).get();
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        pocurementRepository.deleteById(id);
    }
}
