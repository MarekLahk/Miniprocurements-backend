package ee.taltech.procurementSystemBackend.models.search.person;

import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class EmployeeSearch extends PersonSearch<Employee> {

//    public EmployeeSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name) {
//        super(limit, page, sort, dir, name);
//    }

    @Override
    public String toString() {
        return "EmployeeSearch{} " + super.toString();
    }
}
