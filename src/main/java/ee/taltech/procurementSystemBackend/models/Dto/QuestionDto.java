package ee.taltech.procurementSystemBackend.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Integer questionId;
    private String askerId;
    private Integer procurementId;
    private String question;
    private Timestamp timeAsked;
}
