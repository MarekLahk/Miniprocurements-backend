package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcurementService extends ServiceBase<Procurement, ProcurementDto> {

    private final PocurementRepository pocurementRepository;
    private final AuthUtils authUtils;
    private final ProcurementUtils procurementUtils;

    public ProcurementService(RepositoryInterface<Procurement> repository,
                              PocurementRepository pocurementRepository,
                              AuthUtils authUtils, ProcurementUtils procurementUtils) {
        super(repository, ProcurementMapper.INSTANCE);
        this.pocurementRepository = pocurementRepository;
        this.authUtils = authUtils;
        this.procurementUtils = procurementUtils;
    }

    public ProcurementDto addProcurement(ProcurementDto dto, Authentication authentication) {
        Procurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementException("No procurement dto provided"));
        procurementUtils.checkProcurementDeadlineIsNotInPast(dto.getDeadline());
        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        procurement.setCreatedById(creatorId);
        procurement.setStatus((short) 1);
        procurement.setCreatedAt(null);
        boolean hasContract = dto.getContractId() != null;
        procurement.setHasContract(dto.getContractId() != null);
        if (hasContract) {
            Integer contractSubId = pocurementRepository.countByContractId(dto.getContractId()) + 1;
            procurement.setContractSubId(contractSubId);
        }
        return toDtoOptional(pocurementRepository.save(procurement))
                .orElseThrow(() -> new ProcurementException("Could not save procurement"));
    }

    public ProcurementDto updateProcurement(Integer id,
                                            ProcurementDto dto,
                                            Authentication authentication) {
        Optional<Procurement> optionalProcurement = pocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getId(), optionalProcurement);
        procurementUtils.checkProcurementDeadlineIsNotInPast(dto.getDeadline());
        // optional isPresent is checked in utils
        Integer addedBy = optionalProcurement.get().getCreatedById();

        // get this to take some values that cannot be passed by put method
        Procurement initialProcurement = optionalProcurement.get();

        Procurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementException("No procurement dto provided"));

        procurement.setId(id);
        procurement.setCreatedById(person.getId());
        procurement.setCreatedAt(initialProcurement.getCreatedAt());
        procurement.setCreatedById(addedBy);
        boolean hasContract = dto.getContractId() != null;
        procurement.setHasContract(dto.getContractId() != null);
        if (hasContract) {
            Integer contractSubId = pocurementRepository.countByContractId(dto.getContractId()) + 1;
            procurement.setContractSubId(contractSubId);
        }
        procurement.setStatus(initialProcurement.getStatus());
        return toDtoOptional(pocurementRepository.save(procurement))
                .orElseThrow(() -> new ProcurementException("Could not update procurement"));
    }

    public ProcurementDto patchProcurementStatus(Integer id, ProcurementDto dto, Authentication authentication) {
        Optional<Procurement> optionalProcurement = pocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getId(), optionalProcurement);
        if (dto.getStatus() == null) throw new ProcurementException("Provided status is not present");
        // optional isPresent is checked in utils
        Procurement procurement = optionalProcurement.get();
        procurement.setStatus(dto.getStatus());
        procurementUtils.checkProcurementBeforeStatusPatch(procurement);
        return toDtoOptional(pocurementRepository.save(procurement)).get();
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        pocurementRepository.deleteById(id);
    }
}