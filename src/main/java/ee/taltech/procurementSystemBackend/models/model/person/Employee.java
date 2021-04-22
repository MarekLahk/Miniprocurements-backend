package ee.taltech.procurementSystemBackend.models.model.person;


import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employee")
@PrimaryKeyJoinColumn(name = "employee_id")
@JGlobalMap
public class Employee extends Person {

    public Employee(EmployeeDto employeeDto) {
        super(employeeDto);
    }

}
