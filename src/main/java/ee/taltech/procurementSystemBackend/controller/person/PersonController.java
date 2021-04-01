package ee.taltech.procurementSystemBackend.controller.person;


import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.model.search.person.PersonSearch;
import ee.taltech.procurementSystemBackend.service.person.PersonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/person")
public class PersonController extends PersonControllerInterface<Person, PersonSearch<Person>>{

    public PersonController(PersonService personService) {
        super(personService);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
