package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService extends PersonServiceInterface<Person> {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Optional<Person> getPersonByEmail(String email) {
        return personRepository.findPersonByeMail(email);
    }


}
