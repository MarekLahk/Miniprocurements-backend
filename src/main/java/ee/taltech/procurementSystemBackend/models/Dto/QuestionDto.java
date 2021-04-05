package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto extends DtoBase {

    private Integer questionId;
    @NotNull(message = "Asker id cannot be null.")
    private String askerId;
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;
    @NotBlank(message = "Question cannot be null or blank.")
    private String question;
    private Timestamp timeAsked;
}
