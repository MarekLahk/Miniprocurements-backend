package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class PersonServiceInterface<T extends Person> {

    private final PersonRepositoryInterface<T> personRepository;

    public List<T> getByParams(SearchObject<T> searchObject) {
        System.out.println(searchObject);
        return personRepository.findAll(searchObject.generateMatchers());
    }

    public Optional<T> getPersonById(Integer id) {
        return personRepository.findById(id);
    }


}
