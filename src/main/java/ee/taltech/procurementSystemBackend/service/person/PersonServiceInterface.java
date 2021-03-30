package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.model.person.search.PersonSearch;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class PersonServiceInterface<T extends Person> {

    private final PersonRepositoryInterface<T> personRepository;

    public List<T> getByParams(SearchObject<T> searchObject) {
        return personRepository.findAll(searchObject.generateMatchers());
    }

    public Optional<T> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Optional<T> getPersonByEmail(String email) {
        return personRepository.findPersonByeMail(email);
    }

    public Optional<T> getPersonByeMail(String email) {
        return personRepository.findPersonByeMail(email);
    }

    public Optional<T> getByTimeOfRegisterBetween(LocalDateTime timeOfRegisterBefore, LocalDateTime timeOfRegisterAfter) {
        return personRepository.findByTimeOfRegisterBetween(timeOfRegisterBefore, timeOfRegisterAfter);
    }

    public Optional<T> getByTimeOfRegisterAfter(LocalDateTime timeOfRegister) {
        return personRepository.findByTimeOfRegisterAfter(timeOfRegister);
    }

    public Optional<T> getByTimeOfRegisterBefore(LocalDateTime timeOfRegister) {
        return personRepository.findByTimeOfRegisterBefore(timeOfRegister);
    }

}
