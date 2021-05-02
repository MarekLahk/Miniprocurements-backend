package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Schema(name = "ProcurementPartnerDto", description = "DTO for procurement partner links")
public class ProcurementPartnerDto extends DtoBase {

//    @Schema(title="ID of the link", example = "")
    private Integer id;

    @Schema(title="UUID of the link")
    private UUID linkId;

    @Schema(title="ID of the procurement in the link", example = "3")
    @NotNull(message = "procurementId cannot be null")
    private Integer procurementId;

    @Schema(title="ID of the partner in the link", example = "2")
    @NotNull(message = "partnerId cannot be null")
    private Integer partnerId;

}