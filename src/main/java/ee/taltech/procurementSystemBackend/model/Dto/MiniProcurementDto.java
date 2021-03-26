package ee.taltech.procurementSystemBackend.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
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
