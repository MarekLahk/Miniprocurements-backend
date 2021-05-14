package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "QuestionInfoDto", description = "DTO for Question Info")
public class QuestionInfoDto extends DtoBase {
    @NotBlank(message = "Question cannot be blank.")
    @NotNull(message = "Question cannot be null.")
    @Schema(title="question of QuestionInfoDto")
    private String question;

    @Schema(title="replies of QuestionInfoDto")
    private Set<ReplyDto> replies;

}
