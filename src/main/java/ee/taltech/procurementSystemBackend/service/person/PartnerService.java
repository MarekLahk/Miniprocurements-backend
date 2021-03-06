package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.exception.PersonException;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.PartnerMapper;
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
        partner.setId(null);
        partner.setCreatedAt(LocalDateTime.now());
        return toDtoOptional(partnerRepository.save(partner)).get();
    }


    public PartnerDto updatePartner(Integer id, PartnerDto dto) {
        Partner currentPartner = partnerRepository.findById(id)
                .orElseThrow(() -> new PersonException("Person with such id does not exist."));
        Optional<String> emailOptional = Optional.ofNullable(dto.getEMail());
        if (emailOptional.isPresent()) {
            Optional<Person> personOptional = personRepository.findByeMailAndId(emailOptional.get(), id);
            if (personOptional.isPresent()) {
                if (!personOptional.get().getId().equals(id))
                throw new PersonException("Person with such email already exists.");
            }
        }

        currentPartner.setId(id);
        currentPartner.setEMail(dto.getEMail());
        currentPartner.setPartnerInfo(dto.getPartnerInfo());
        currentPartner.setRegNr(dto.getRegNr());
        currentPartner.setName(dto.getName());
        return toDtoOptional(partnerRepository.save(currentPartner)).get();
    }
}
