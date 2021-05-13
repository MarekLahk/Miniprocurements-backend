package ee.taltech.procurementSystemBackend.models.Dto;

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
@Schema(name = "BidDto", description = "DTO for bid")
public class BidDto extends DtoBase {
    @Schema(title="ID of bid")
    private Integer id;

    @Schema(title="ID of link")
    @NotNull(message="LinkId cannot be null")
    private UUID LinkId;

    @Schema(title="Value of the bid")
    private Long bidValue;

    @Schema(title="Description of the bid")
    private String description;

    @Schema(title="Status of the bid")
    @NotNull(message="bidStatus cannot be null")
    private Integer bidStatus;

    @Schema(title="ID of procurement")
    private Integer procurementId;
}
