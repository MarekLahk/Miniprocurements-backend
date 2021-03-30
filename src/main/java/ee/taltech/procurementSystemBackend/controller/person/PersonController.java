package ee.taltech.procurementSystemBackend.controller.person;


import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.model.person.search.PersonSearch;
import ee.taltech.procurementSystemBackend.service.person.PersonService;
import ee.taltech.procurementSystemBackend.service.person.PersonServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/person")
public class PersonController extends PersonControllerInterface<Person, PersonSearch<Person>>{

    public PersonController(PersonService personService) {
        super(personService);
    }


}
