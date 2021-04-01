package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.model.person.Employee;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends PersonServiceInterface<Employee> {
    public EmployeeService(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }
}
