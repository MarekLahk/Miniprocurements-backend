package ee.taltech.procurementSystemBackend.service.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import ee.taltech.procurementSystemBackend.models.mapper.person.EmployeeMapper;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends PersonServiceInterface<Employee , EmployeeDto> {
    public EmployeeService(EmployeeRepository employeeRepository) {
        super(employeeRepository, EmployeeMapper.INSTANCE);
    }
}
