package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.QuestionException;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class QuestionService extends ServiceBase<Question, QuestionDto> {

    private final QuestionRepository questionRepository;
    private final MiniprocurementRepository procurementRepository;

    public QuestionService(RepositoryInterface<Question> repository, QuestionRepository questionRepository, MiniprocurementRepository procurementRepository) {
        super(repository, QuestionMapper.INSTANCE);
        this.questionRepository = questionRepository;
        this.procurementRepository = procurementRepository;
    }

    public QuestionDto addQuestion(QuestionDto dto) {
        if (procurementRepository.findById(dto.getProcurementId()).isEmpty()) {
            throw new QuestionException(String.format(
                    "Procurement with id [%d] does not exist",
                    dto.getProcurementId()));
        }
        Question question = toModelOptional(dto)
                .orElseThrow(() -> new QuestionException("No question dto provided"));
        question.setTimeAsked(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(questionRepository.save(question))
                .orElseThrow(() -> new QuestionException("Could not save question"));
    }

    @Deprecated
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
