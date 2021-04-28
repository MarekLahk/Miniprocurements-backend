package ee.taltech.procurementSystemBackend.models.model.person;


import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Miniprocurement> procurements;

}
