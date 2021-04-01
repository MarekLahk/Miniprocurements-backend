package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.search.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class PersonServiceInterface<T extends Person> {

    private final PersonRepositoryInterface<T> personRepository;

    public List<T> getByParams(SearchObject<T> searchObject) {
        System.out.println(searchObject);
        return personRepository.findAll(searchObject.getSearchSpecPack(),  PageRequest.of(0, 10, Sort.by("personID").descending())).getContent();
    }

    public Optional<T> getPersonById(Integer id) {
        return personRepository.findById(id);
    }


}
