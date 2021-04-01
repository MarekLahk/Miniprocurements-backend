package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.model.search.SearchObject;
import ee.taltech.procurementSystemBackend.service.ServiceBase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class ControllerBase<T, O extends SearchObject<T>  > {

    private final ServiceBase<T> personService;

    @GetMapping()
    public List<T> getByParams(O searchObject) {
        System.out.println(searchObject);
        return personService.getByParams(searchObject);
    }


    @GetMapping("{id}")
    public Optional<T> getById(@PathVariable Integer id) {
        return personService.getById(id);
    }
//    @GetMapping()
//    public Optional<T> getPersonRegBefore(@RequestParam(name = "regbefore") LocalDateTime regTime) {
//        return personService.getByTimeOfRegisterBefore(regTime);
//    }

}
