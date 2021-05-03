package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionInfoDto extends DtoBase {

    @NotBlank(message = "Question cannot be null or blank.")
    private String question;
    private Set<ReplyDto> replies;

}
