package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.PersonMapperInterface;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import ee.taltech.procurementSystemBackend.service.ServiceBase;


public abstract class PersonServiceInterface<ModelT extends Person, DtoT extends PersonDto> extends ServiceBase<ModelT, DtoT> {

    private final PersonRepositoryInterface<ModelT> personRepository;

    public PersonServiceInterface(PersonRepositoryInterface<ModelT> personRepository, PersonMapperInterface<ModelT, DtoT> mapper) {
        super(personRepository, mapper);
        this.personRepository = personRepository;
    }



}
