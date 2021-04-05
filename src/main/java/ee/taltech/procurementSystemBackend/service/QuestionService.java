package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.QuestionUtils;
import org.springframework.stereotype.Service;


@Service
public class QuestionService extends ServiceBase<Question, QuestionDto> {

    private final QuestionRepository questionRepository;
    private final QuestionUtils questionUtils;

    public QuestionService(RepositoryInterface<Question> repository, QuestionRepository questionRepository, QuestionUtils questionUtils) {
        super(repository, QuestionMapper.INSTANCE);
        this.questionRepository = questionRepository;
        this.questionUtils = questionUtils;
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
