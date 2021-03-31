package ee.taltech.procurementSystemBackend.model.person.search;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Employee;
import ee.taltech.procurementSystemBackend.model.person.Partner;

import java.time.LocalDateTime;

public class EmployeeSearch extends PersonSearch<Employee> implements SearchObject<Employee> {

    public EmployeeSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit) {
        super(before, after, name, limit);
    }

}
