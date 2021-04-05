package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto extends DtoBase {

    private Integer replyId;
    @NotNull(message = "Replier id cannot be null.")
    private Integer replierId;
    @NotNull(message = "Question id cannot be null.")
    private Integer questionId;
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;
    private String reply;
    private Timestamp timeReplied;
}
