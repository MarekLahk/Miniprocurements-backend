package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import ee.taltech.procurementSystemBackend.service.ServiceBase;

import java.util.Optional;


public abstract class PersonServiceInterface<T extends Person> extends ServiceBase<T> {

    PersonRepositoryInterface<T> personRepository;

    public PersonServiceInterface(PersonRepositoryInterface<T> personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    public Optional<T> getById(Integer id) {
        return personRepository.findById(id);
    }


}
