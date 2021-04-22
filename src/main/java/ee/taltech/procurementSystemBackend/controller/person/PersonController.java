package ee.taltech.procurementSystemBackend.controller.person;


import ee.taltech.procurementSystemBackend.controller.ControllerBase;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.models.search.person.PersonSearch;
import ee.taltech.procurementSystemBackend.service.person.PersonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/person")
public class PersonController extends ControllerBase<Person, PersonDto, PersonSearch<Person>> {

    public PersonController(PersonService personService) {
        super(personService, PersonDto.class);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
