package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ContractDto", description = "DTO for ContractDto")
public class ContractDto extends DtoBase  {
    @Schema(title="id of ContractDto")
    private Integer id;

    @NotNull(message = "referenceNumber cannot be null")
    @Schema(title="referenceNumber of ContractDto")
    private Integer referenceNumber;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @Schema(title="name of ContractDto")
    private String name;

    @Min(1)
    @Max(2)
    @NotNull(message = "Status cannot be null, See https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/wikis/klassifikaatorid for valid options.")
    @Schema(title="status of ContractDto")
    private Integer status;

    @Schema(title = "description of ContractDto")
    private String description;
}