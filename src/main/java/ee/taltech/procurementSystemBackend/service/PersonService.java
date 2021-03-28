package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Optional<Person> getEmployeeById(Integer id) {
        return personRepository.findById(id);
    }
}
