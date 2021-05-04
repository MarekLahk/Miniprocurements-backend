package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
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
public class ContractDto extends DtoBase  {

    private Integer contractId;

    @NotNull(message = "contractReferenceNumber cannot be null")
    private Integer contractReferenceNumber;
    private Integer procurementTemplateId;
    private Integer bidTemplateId;

    @NotNull(message = "contractName cannot be null")
    private String contractName;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Size(min = 1, max = 2, message = "Status has to be 1 or 2. , See https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/wikis/klassifikaatorid for valid options.")
    @NotNull(message = "Status cannot be null, See https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/wikis/klassifikaatorid for valid options.")
    private Integer status;
}