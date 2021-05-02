package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ReplyException;
import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.mapper.ReplyMapper;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ReplyService extends ServiceBase<Reply, ReplyDto> {

    private final ReplyRepository replyRepository;
    private final QuestionRepository questionRepository;
    private final AuthUtils authUtils;

    public ReplyService(RepositoryInterface<Reply> repository, ReplyRepository replyRepository, QuestionRepository questionRepository, AuthUtils authUtils) {
        super(repository, ReplyMapper.INSTANCE);
        this.replyRepository = replyRepository;
        this.questionRepository = questionRepository;
        this.authUtils = authUtils;
    }

    public ReplyDto addReply(ReplyDto dto, Authentication authentication) {
        Person person = authUtils.getPersonToPerformOperations(authentication);
        if (questionRepository.findByIdAndProcurementId(
                dto.getQuestionId(), dto.getProcurementId()).isEmpty()) {
            throw new ReplyException(String.format(
                    "Question with id [%d] and procurement id [%d] does not exist",
                    dto.getQuestionId(), dto.getProcurementId()));
        }
        Reply reply = toModelOptional(dto)
                .orElseThrow(() -> new ReplyException("No reply dto provided"));
        reply.setCreatedAt(null);
        reply.setReplierId(person.getId());
        return toDtoOptional(replyRepository.save(reply)).get();
    }

    @Deprecated
    public void deleteReply(Integer id) {
        replyRepository.deleteById(id);
    }
}
