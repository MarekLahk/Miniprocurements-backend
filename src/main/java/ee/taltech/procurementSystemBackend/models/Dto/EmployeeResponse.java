package ee.taltech.procurementSystemBackend.models.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "EmployeeResponse", description = "EmployeeResponse")
public class EmployeeResponse {
    @Schema(title="employeeId of EmployeeResponse")
    @NotNull(message="employeeId cannot be null")
    private Integer employeeId;

    @Schema(title="username of EmployeeResponse")
    private String username;

    @Schema(title="fullName of EmployeeResponse")
    private String fullName;
}
