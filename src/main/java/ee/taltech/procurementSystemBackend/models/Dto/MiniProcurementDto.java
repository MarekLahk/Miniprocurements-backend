package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniProcurementDto extends DtoBase  {

    private Integer procurementId;
    private String procurementName;
    private Integer amount;
    private String description;
    private String requirements;
    private Integer contractId;
    private Timestamp timeAdded;
    private Integer addedBy;
    private Timestamp deadline;
    private Short status;
    private Timestamp timeFinished;
}
