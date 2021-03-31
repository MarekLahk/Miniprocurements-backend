package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.model.person.Employee;
import ee.taltech.procurementSystemBackend.model.person.search.EmployeeSearch;
import ee.taltech.procurementSystemBackend.service.person.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController extends PersonControllerInterface<Employee, EmployeeSearch> {
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
