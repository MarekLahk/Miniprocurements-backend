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
}
