package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "ReplyDto", description = "DTO for Replies")
public class ReplyDto extends DtoBase {
    @Schema(title="id of ReplyDto")
    private Integer id;

    @NotNull(message = "Replier id cannot be null.")
    @Schema(title="replierId of ReplyDto")
    private Integer replierId;

    @NotNull(message = "Question id cannot be null.")
    @Schema(title="questionId of ReplyDto")
    private Integer questionId;

    @NotNull(message = "Procurement id cannot be null.")
    @Schema(title="procurementId of ReplyDto")
    private Integer procurementId;

    @Schema(title="reply of ReplyDto")
    private String reply;
}
