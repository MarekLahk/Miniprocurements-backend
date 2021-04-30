package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.QuestionException;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;


@Service
public class QuestionService extends ServiceBase<Question, QuestionDto> {

    private final QuestionRepository questionRepository;
    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;

    public QuestionService(RepositoryInterface<Question> repository,
                           QuestionRepository questionRepository,
                           MiniprocurementPartnerRepository miniprocurementPartnerRepository) {
        super(repository, QuestionMapper.INSTANCE);
        this.questionRepository = questionRepository;
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
    }

    public QuestionDto addQuestion(UUID bidderLinkId, QuestionDto dto) {
        MiniprocurementPartner partner = miniprocurementPartnerRepository
                .findByMiniprocurementPartnerLinkId(bidderLinkId)
                .orElseThrow(() -> new QuestionException(String.format(
                "Procurement partner with link [%s] does not exist",
                bidderLinkId)));
        Question question = toModelOptional(dto)
                .orElseThrow(() -> new QuestionException("No question dto provided"));
        question.setBidderLinkId(bidderLinkId);
        question.setTimeAsked(new Timestamp(System.currentTimeMillis()));
        question.setProcurementId(partner.getMiniprocurementPartnerProcurementId());
        return toDtoOptional(questionRepository.save(question)).get();
    }

    @Deprecated
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
