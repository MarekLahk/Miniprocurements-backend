package ee.taltech.procurementSystemBackend.model.Dto;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BidDto {
    private Integer bidId;
    private Long bidValue;
    private String description;
    private Timestamp timeOfRegister;
    private Integer bidStatus;
}
