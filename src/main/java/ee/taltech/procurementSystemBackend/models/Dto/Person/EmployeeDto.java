package ee.taltech.procurementSystemBackend.models.Dto.Person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JGlobalMap
public class EmployeeDto extends PersonDto{

    public EmployeeDto(Employee employee) {
        super(employee);
    }

}
