package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.QuestionException;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.ProcurementPartnerRepository;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class QuestionService extends ServiceBase<Question, QuestionDto> {

    private final QuestionRepository questionRepository;
    private final ProcurementPartnerRepository procurementPartnerRepository;

    public QuestionService(RepositoryInterface<Question> repository,
                           QuestionRepository questionRepository,
                           ProcurementPartnerRepository procurementPartnerRepository) {
        super(repository, QuestionMapper.INSTANCE);
        this.questionRepository = questionRepository;
        this.procurementPartnerRepository = procurementPartnerRepository;
    }

    public QuestionDto addQuestion(UUID bidderLinkId, QuestionDto dto) {
        ProcurementPartner partner = procurementPartnerRepository
                .findByLinkId(bidderLinkId)
                .orElseThrow(() -> new QuestionException(String.format(
                "Procurement partner with link [%s] does not exist",
                bidderLinkId)));
        Question question = toModelOptional(dto)
                .orElseThrow(() -> new QuestionException("No question dto provided"));
        question.setLinkId(bidderLinkId);
        question.setCreatedAt(null);
        question.setProcurementId(partner.getProcurementId());
        return toDtoOptional(questionRepository.save(question)).get();
    }

    @Deprecated
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
