package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.model.person.search.PersonSearch;
import ee.taltech.procurementSystemBackend.service.person.PersonServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class PersonControllerInterface<T extends Person, O extends SearchObject<T>  > {

    private final PersonServiceInterface<T> personService;

    @GetMapping()
    public List<T> getByParams(O searchObject) {
        System.out.println(searchObject);
        System.out.println(searchObject.getClass());
        return personService.getByParams(searchObject);
    }


    @GetMapping("{id}")
    public Optional<T> getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }
//    @GetMapping()
//    public Optional<T> getPersonRegBefore(@RequestParam(name = "regbefore") LocalDateTime regTime) {
//        return personService.getByTimeOfRegisterBefore(regTime);
//    }

}
