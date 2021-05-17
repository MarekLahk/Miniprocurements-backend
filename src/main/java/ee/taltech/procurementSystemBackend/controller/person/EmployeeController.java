package ee.taltech.procurementSystemBackend.controller.person;

import ee.taltech.procurementSystemBackend.controller.ControllerBase;
import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.search.person.EmployeeSearch;
import ee.taltech.procurementSystemBackend.service.person.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employees")
public class EmployeeController extends ControllerBase<Employee, EmployeeDto, EmployeeSearch> {
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }

    //Note: Contains all search attributes of PersonControllerInterface
}
