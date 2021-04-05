package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto extends DtoBase {

    private Integer replyId;
    private Integer replierId;
    private Integer questionId;
    private Integer procurementId;
    private String reply;
    private Timestamp timeReplied;
}
