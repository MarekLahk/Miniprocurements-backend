package ee.taltech.procurementSystemBackend.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniProcurementDto {

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
