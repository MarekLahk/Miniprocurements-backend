package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
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

    @Schema(title="replierId of ReplyDto")
    private Integer replierId;

    @NotNull(message = "Question id cannot be null.")
    @Schema(title="questionId of ReplyDto")
    private Integer questionId;

    @NotNull(message = "Procurement id cannot be null.")
    @Schema(title="procurementId of ReplyDto")
    private Integer procurementId;

    @NotNull(message = "Reply message should not be null")
    @NotBlank(message = "Reply message should not be empty")
    @Schema(title="reply of ReplyDto")
    private String reply;

}
