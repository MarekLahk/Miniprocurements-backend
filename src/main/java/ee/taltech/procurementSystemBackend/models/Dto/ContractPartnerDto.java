package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Schema(name = "ContractPartnerDto", description = "DTO for contract partner links")
public class ContractPartnerDto extends DtoBase {
    @Schema(title="ID of the link", example = "")
    private Integer id;

    @Schema(title="ID of the contract in the link", example = "3")
    @NotNull(message = "ContractId cannot be null")
    private Integer ContractId;

    @Schema(title="ID of the partner in the link", example = "2")
    @NotNull(message = "PartnerId cannot be null")
    private Integer PartnerId;

}