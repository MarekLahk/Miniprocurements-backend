package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDto extends DtoBase {
    private Integer id;
    private UUID LinkId;
    private Long bidValue;
    private String description;
    private Integer bidStatus;
    private Integer procurementId;
}
