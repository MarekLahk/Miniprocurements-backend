package ee.taltech.procurementSystemBackend.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {

    private Integer replyId;
    private Integer replierId;
    private Integer questionId;
    private Integer procurementId;
    private String reply;
    private Timestamp timeReplied;
}
