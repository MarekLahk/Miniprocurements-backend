package ee.taltech.procurementSystemBackend.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Integer employeeId;
    private String username;
    private String fullName;
}
