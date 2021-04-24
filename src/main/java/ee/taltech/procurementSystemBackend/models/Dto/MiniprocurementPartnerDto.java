package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import com.googlecode.jmapper.annotations.JGlobalMap;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.context.annotation.Description;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Schema(name = "MiniprocurementPartnerDto", description = "DTO for miniprocurement partner links")
public class MiniprocurementPartnerDto extends DtoBase {
    @Schema(title="ID of the link", example = "")
    private Integer miniprocurementPartnerId;

    @Schema(title="UUID of the link", example = "")
    private UUID miniprocurementPartnerLinkId;

    @Schema(title="ID of the procurement in the link", example = "2")
    @NotNull(message = "miniprocurementPartnerProcurementId cannot be null")
    private Integer miniprocurementPartnerProcurementId;

    @Schema(title="ID of the partner in the link", example = "3")
    @NotNull(message = "miniprocurementPartnerPartnerId cannot be null")
    private Integer miniprocurementPartnerPartnerId;

//  private String miniprocurementPartnerTimeAdded;

    @Schema(title="Time link was first link")
    private String miniprocurementPartnerLinkFirstAccessed;
}