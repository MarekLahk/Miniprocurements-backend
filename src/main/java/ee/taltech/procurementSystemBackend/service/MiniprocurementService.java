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
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MiniprocurementService extends ServiceBase<Miniprocurement, MiniProcurementDto> {

    private final MiniprocurementRepository miniprocurementRepository;
    private final PersonRepository personRepository;

    public MiniprocurementService(RepositoryInterface<Miniprocurement> repository,
                                  MiniprocurementRepository miniprocurementRepository,
                                  PersonRepository personRepository) {
        super(repository, MiniprocurementMapper.INSTANCE);
        this.miniprocurementRepository = miniprocurementRepository;
        this.personRepository = personRepository;
    }

    public MiniProcurementDto addProcurement(MiniProcurementDto dto) {
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));
        procurement.setTimeAdded(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not save procurement"));
    }

    public MiniProcurementDto updateProcurement(Integer id,
                                                MiniProcurementDto dto,
                                                Authentication authentication) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        Person person = personRepository.findByPersonEmail(username)
                .orElseThrow(() -> new RequestedObjectNotFoundException("Such person does not exist"));

        if (miniprocurementRepository.findByProcurementIdAndAddedBy(id, person.getPersonID()).isEmpty()) {
            throw new AuthException("This person does not have permission to update this procurement");
        }

        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", id));
        }
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));



        procurement.setProcurementId(id);
        procurement.setTimeAdded(dto.getTimeAdded());
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not update procurement"));
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        miniprocurementRepository.deleteById(id);
    }

}
