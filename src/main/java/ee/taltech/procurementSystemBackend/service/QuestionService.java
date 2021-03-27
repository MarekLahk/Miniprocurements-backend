package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.model.Question;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.utils.QuestionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionUtils questionUtils;

    public QuestionDto getQuestionByQuestionId(Integer id) {
        return questionUtils.convertFromQuestionToDto(
                questionRepository.findByQuestionId(id)
        );
    }

    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionUtils::convertFromQuestionToDto)
                .collect(Collectors.toList());
    }

    public List<QuestionDto> findAllByProcurementId(Integer procurementId) {
        return questionRepository.findAllByProcurementId(procurementId).stream()
                .map(questionUtils::convertFromQuestionToDto)
                .collect(Collectors.toList());
    }


    public QuestionDto addQuestion(QuestionDto dto) {
        Question question = questionUtils.convertFromDtoToQuestion(dto);
        return questionUtils.convertFromQuestionToDto(
                questionRepository.save(question)
        );
    }
}
