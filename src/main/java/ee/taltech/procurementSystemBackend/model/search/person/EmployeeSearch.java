package ee.taltech.procurementSystemBackend.model.search.person;

import ee.taltech.procurementSystemBackend.model.search.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Employee;

import java.time.LocalDateTime;

public class EmployeeSearch extends PersonSearch<Employee> {

    public EmployeeSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit) {
        super(before, after, name, limit);
    }

}
