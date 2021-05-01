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
    private Integer contractPartnerId;

    @Schema(title="UUID of the link", example = "")
    private UUID contractPartnerLinkId;

    @Schema(title="ID of the procurement in the link", example = "3")
    @NotNull(message = "contractPartnerProcurementId cannot be null")
    private Integer contractPartnerProcurementId;

    @Schema(title="ID of the partner in the link", example = "2")
    @NotNull(message = "contractPartnerPartnerId cannot be null")
    private Integer contractPartnerPartnerId;

//  private String contractPartnerTimeAdded;

    @Schema(title="Time link was first link", example = "")
    private String contractPartnerLinkFirstAccessed;
}