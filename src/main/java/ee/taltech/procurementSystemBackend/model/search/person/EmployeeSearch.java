package ee.taltech.procurementSystemBackend.model.search.person;

import ee.taltech.procurementSystemBackend.model.person.Employee;
import org.springframework.data.domain.Sort.Direction;

import java.time.LocalDateTime;

public class EmployeeSearch extends PersonSearch<Employee> {

    public EmployeeSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name) {
        super(limit, page, sort, dir, before, after, name);
    }

    @Override
    public String toString() {
        return "EmployeeSearch{} " + super.toString();
    }
}
