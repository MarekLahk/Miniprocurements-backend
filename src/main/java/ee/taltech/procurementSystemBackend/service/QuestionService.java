package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.utils.QuestionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionUtils questionUtils;

    public QuestionDto getQuestionByQuestionId(Integer id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Question with id [%d] does not exist", id));
        }
        Question question = questionOptional.get();
        return questionUtils.convertFromQuestionToDto(question);
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

    @Deprecated
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
