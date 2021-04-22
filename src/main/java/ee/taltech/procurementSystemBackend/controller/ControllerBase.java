package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.service.ServiceBase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class ControllerBase<ModelT extends ModelBase, DtoT extends DtoBase, O extends SearchObject<ModelT>  > {

    private final ServiceBase<ModelT, DtoT> service;
    private final Class<DtoT> dto;

    public ControllerBase(ServiceBase<ModelT, DtoT> personService, Class<ModelT> model, Class<DtoT> dto) {
        this.service = personService;
        this.dto = dto;
    }

    @GetMapping()
    public List<DtoT> getByParams(O searchObject) {
        System.out.println(searchObject);
        return service.getByParams(searchObject);
    }


    @GetMapping("{id}")
    public Optional<DtoT> getById(@PathVariable Integer id) {
        return service.getById(id);
    }
//    @GetMapping()
//    public Optional<T> getPersonRegBefore(@RequestParam(name = "regbefore") LocalDateTime regTime) {
//        return personService.getByTimeOfRegisterBefore(regTime);
//    }

}
