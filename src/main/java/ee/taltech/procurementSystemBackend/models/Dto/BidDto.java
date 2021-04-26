package ee.taltech.procurementSystemBackend.models.Dto;

import java.sql.Timestamp;
import java.util.UUID;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDto extends DtoBase {
    private Integer bidId;
    private UUID bidderLinkId;
    private Long bidValue;
    private String description;
    private Timestamp timeOfRegister;
    private Integer bidStatus;
    private Integer procurementId;
}
