package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto extends DtoBase {

    private Integer questionId;
    private String askerId;
    private Integer procurementId;
    private String question;
    private Timestamp timeAsked;
}
