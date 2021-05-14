package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Schema(title="procurementTemplateId of ContractDto")
    private Integer procurementTemplateId;

    @Schema(title="bidTemplateId of ContractDto")
    private Integer bidTemplateId;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @Schema(title="name of ContractDto")
    private String name;

    @Size(min = 1, max = 2, message = "Status has to be 1 or 2. , See https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/wikis/klassifikaatorid for valid options.")
    @NotNull(message = "Status cannot be null, See https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/wikis/klassifikaatorid for valid options.")
    @Schema(title="status of ContractDto")
    private Integer status;
}