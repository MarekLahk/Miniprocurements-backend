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
@Schema(name = "QuestionDto", description = "DTO for Question")
public class QuestionDto extends DtoBase {
    @Schema(title="id of QuestionDto")
    private Integer id;

    @Schema(title="procurementId of QuestionDto")
    private Integer procurementId;

    @NotBlank(message = "Question cannot be blank.")
    @NotNull(message = "Question cannot be null.")
    @Schema(title="question of QuestionDto")
    private String question;
}
