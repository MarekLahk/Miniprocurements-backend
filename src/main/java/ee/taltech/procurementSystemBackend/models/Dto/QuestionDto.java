package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto extends DtoBase {

    private Integer questionId;
    private UUID bidderLinkId;
    private Integer procurementId;
    @NotBlank(message = "Question cannot be null or blank.")
    private String question;
    private Timestamp timeAsked;
}
