package ee.taltech.procurementSystemBackend.model.Dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDto {
    private Integer bidId;
    private Long bidValue;
    private String description;
    private Timestamp timeOfRegister;
    private Integer bidStatus;
}
