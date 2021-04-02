package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.PersonMapper;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends PersonServiceInterface<Person, PersonDto> {

    public PersonService(PersonRepository personRepository) {
        super(personRepository, PersonMapper.INSTANCE);
    }



}
