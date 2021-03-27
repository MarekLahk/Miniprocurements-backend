package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.model.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.model.Question;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class QuestionUtils {

    public Question convertFromDtoToQuestion(QuestionDto dto) {
        return Question.builder()
                .askerId(dto.getAskerId())
                .procurementId(dto.getProcurementId())
                .question(dto.getQuestion())
                .timeAsked(new Timestamp(System.currentTimeMillis())).build();
    }

    public QuestionDto convertFromQuestionToDto(Question question) {
        return QuestionDto.builder()
                .questionId(question.getQuestionId())
                .askerId(question.getAskerId())
                .procurementId(question.getProcurementId())
                .question(question.getQuestion())
                .timeAsked(question.getTimeAsked()).build();
    }
}
