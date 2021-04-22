package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MiniprocurementService extends ServiceBase<Miniprocurement, MiniProcurementDto> {

    private final MiniprocurementRepository miniprocurementRepository;
    private final AuthUtils authUtils;
    private final ProcurementUtils procurementUtils;

    public MiniprocurementService(RepositoryInterface<Miniprocurement> repository,
                                  MiniprocurementRepository miniprocurementRepository,
                                  AuthUtils authUtils, ProcurementUtils procurementUtils) {
        super(repository, MiniprocurementMapper.INSTANCE);
        this.miniprocurementRepository = miniprocurementRepository;
        this.authUtils = authUtils;
        this.procurementUtils = procurementUtils;
    }

    public MiniProcurementDto addProcurement(MiniProcurementDto dto, Authentication authentication) {
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));
        procurementUtils.checkProcurementDeadlineIsNotInPast(dto.getDeadline());
        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getPersonID();
        procurement.setAddedBy(creatorId);
        procurement.setStatus((short) 1);
        procurement.setTimeAdded(new Timestamp(System.currentTimeMillis()));
        boolean hasContract = dto.getContractId() != null;
        procurement.setHasContract(dto.getContractId() != null);
        if (hasContract) {
            Integer contractSubId = miniprocurementRepository.countByContractId(dto.getContractId()) + 1;
            procurement.setContractSubId(contractSubId);
        }
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not save procurement"));
    }

    public MiniProcurementDto updateProcurement(Integer id,
                                                MiniProcurementDto dto,
                                                Authentication authentication) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getPersonID(), optionalProcurement);
        procurementUtils.checkProcurementDeadlineIsNotInPast(dto.getDeadline());
        // optional isPresent is checked in utils
        Integer addedBy = optionalProcurement.get().getAddedBy();

        // get this to take some values that cannot be passed by put method
        Miniprocurement initialProcurement = optionalProcurement.get();

        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));

        procurement.setProcurementId(id);
        procurement.setAddedBy(person.getPersonID());
        procurement.setTimeAdded(initialProcurement.getTimeAdded());
        procurement.setAddedBy(addedBy);
        boolean hasContract = dto.getContractId() != null;
        procurement.setHasContract(dto.getContractId() != null);
        if (hasContract) {
            Integer contractSubId = miniprocurementRepository.countByContractId(dto.getContractId()) + 1;
            procurement.setContractSubId(contractSubId);
        }
        procurement.setStatus(initialProcurement.getStatus());
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not update procurement"));
    }

    public MiniProcurementDto patchProcurementStatus(Integer id, MiniProcurementDto dto, Authentication authentication) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        procurementUtils.checkEmployeePermissionAndProcurementPresence(id, person.getPersonID(), optionalProcurement);
        if (dto.getStatus() == null) throw new MiniprocurementException("Provided status is not present");
        // optional isPresent is checked in utils
        Miniprocurement procurement = optionalProcurement.get();
        procurement.setStatus(dto.getStatus());
        procurementUtils.checkProcurementBeforeStatusPatch(procurement);
        return toDtoOptional(procurement).get();
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        miniprocurementRepository.deleteById(id);
    }
}
