package ee.taltech.procurementSystemBackend.controller;


import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/person")
public class PersonController {

    private final PersonService employeeService;

    @GetMapping("{id}")
    public Optional<Person> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }
}
