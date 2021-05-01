package ee.taltech.procurementSystemBackend.models.search.person;

import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import org.springframework.data.domain.Sort.Direction;

import java.time.LocalDateTime;

public class EmployeeSearch extends PersonSearch<Employee> {

    public EmployeeSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name) {
        super(limit, page, sort, dir, name);
    }

    @Override
    public String toString() {
        return "EmployeeSearch{} " + super.toString();
    }
}
