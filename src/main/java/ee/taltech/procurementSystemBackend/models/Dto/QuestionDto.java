package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto extends DtoBase {

    private Integer id;
    private Integer procurementId;
    @NotBlank(message = "Question cannot be null or blank.")
    private String question;

}
