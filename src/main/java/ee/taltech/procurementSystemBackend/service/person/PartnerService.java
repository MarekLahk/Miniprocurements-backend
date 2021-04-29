package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.exception.PersonException;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.PartnerMapper;
import ee.taltech.procurementSystemBackend.models.mapper.person.PersonMapper;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PartnerRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PartnerService extends PersonServiceInterface<Partner, PartnerDto> {

    private final PartnerRepository partnerRepository;
    private final PersonRepository personRepository;

    public PartnerService(PartnerRepository partnerRepository,
                          PersonRepository personRepository) {
        super(partnerRepository, PartnerMapper.INSTANCE);
        this.partnerRepository = partnerRepository;
        this.personRepository = personRepository;
    }

    public PartnerDto addPartner(PartnerDto dto) {
        if (personRepository.findByeMail(dto.getEMail()).isPresent()) {
            throw new PersonException("Person with such email already exists.");
        }
        Partner partner = toModelOptional(dto).orElseThrow(() -> new PersonException("No dto provided"));
        partner.setPersonID(null);
        partner.setTimeOfRegister(LocalDateTime.now());
        return toDtoOptional(partnerRepository.save(partner)).get();
    }


    public PartnerDto updatePartner(Integer id, PartnerDto dto) {
        Person initialPerson = personRepository.findById(id)
                .orElseThrow(() -> new PersonException("Person with such id does not exist."));
        Optional<String> emailOtional = Optional.ofNullable(dto.getEMail());
        if (emailOtional.isPresent() &&
                personRepository.findByeMailAndPersonIDNot(emailOtional.get(), id).isPresent()) {
            throw new PersonException("Person with such email already exists.");
        }
        Partner partner = toModelOptional(dto).get();
        partner.setPersonID(id);
        partner.setTimeOfRegister(initialPerson.getTimeOfRegister());
        return toDtoOptional(partnerRepository.save(partner)).get();
    }
}
