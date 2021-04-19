package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.AuthException;
import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MiniprocurementService extends ServiceBase<Miniprocurement, MiniProcurementDto> {

    private final MiniprocurementRepository miniprocurementRepository;
    private final PersonRepository personRepository;
    private final AuthUtils authUtils;

    public MiniprocurementService(RepositoryInterface<Miniprocurement> repository,
                                  MiniprocurementRepository miniprocurementRepository,
                                  PersonRepository personRepository,
                                  AuthUtils authUtils) {
        super(repository, MiniprocurementMapper.INSTANCE);
        this.miniprocurementRepository = miniprocurementRepository;
        this.personRepository = personRepository;
        this.authUtils = authUtils;
    }

    public MiniProcurementDto addProcurement(MiniProcurementDto dto, Authentication authentication) {
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));
        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getPersonID();
        procurement.setAddedBy(creatorId);
        procurement.setTimeAdded(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not save procurement"));
    }

    public MiniProcurementDto updateProcurement(Integer id,
                                                MiniProcurementDto dto,
                                                Authentication authentication) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        if (miniprocurementRepository.findByProcurementIdAndAddedBy(id, person.getPersonID()).isEmpty()) {
            throw new AuthException("This person does not have permission to update this procurement");
        }

        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", id));
        }
        Integer addedBy = optionalProcurement.get().getAddedBy();
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));

        procurement.setProcurementId(id);
        procurement.setAddedBy(person.getPersonID());
        procurement.setTimeAdded(dto.getTimeAdded());
        procurement.setAddedBy(addedBy);
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not update procurement"));
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        miniprocurementRepository.deleteById(id);
    }

}
