package ee.taltech.procurementSystemBackend.models.Dto.Person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
//@AllArgsConstructor
@JGlobalMap
@Schema(name = "EmployeeDto", description = "DTO for employee")
public class EmployeeDto extends PersonDto{
    //Employee Dto is empty because employee has no additional data associated with it. Currently it only has data inherented from Person. If in the future there is a need to store data unique to Employee then it would go there.
}
