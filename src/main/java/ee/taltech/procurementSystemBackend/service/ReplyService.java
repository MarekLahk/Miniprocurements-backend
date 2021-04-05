package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ReplyException;
import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.mapper.ReplyMapper;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.ReplyUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ReplyService extends ServiceBase<Reply, ReplyDto> {

    private final ReplyRepository replyRepository;
    private final ReplyUtils replyUtils;
    private final QuestionRepository questionRepository;

    public ReplyService(RepositoryInterface<Reply> repository, ReplyRepository replyRepository, ReplyUtils replyUtils, QuestionRepository questionRepository) {
        super(repository, ReplyMapper.INSTANCE);
        this.replyRepository = replyRepository;
        this.replyUtils = replyUtils;
        this.questionRepository = questionRepository;
    }

    public ReplyDto addReply(ReplyDto dto) {
        if (questionRepository.findByQuestionIdAndProcurementId(
                dto.getQuestionId(), dto.getProcurementId()).isEmpty()) {
            throw new ReplyException(String.format(
                    "Question with id [%d] and procurement id [%d] does not exist",
                    dto.getQuestionId(), dto.getProcurementId()));
        }
        Reply reply = replyUtils.convertFromDtoToReply(dto);
        reply.setTimeReplied(new Timestamp(System.currentTimeMillis()));
        return replyUtils.convertFromReplyToDto(
                replyRepository.save(reply)
        );
    }

    @Deprecated
    public void deleteReply(Integer id) {
        replyRepository.deleteById(id);
    }
}
