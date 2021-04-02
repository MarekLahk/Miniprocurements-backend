package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ReplyUtils {

    public Reply convertFromDtoToReply(ReplyDto dto) {
        return Reply.builder()
                .replierId(dto.getReplierId())
                .questionId(dto.getQuestionId())
                .procurementId(dto.getProcurementId())
                .reply(dto.getReply())
                .timeReplied(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public ReplyDto convertFromReplyToDto(Reply reply) {
        return ReplyDto.builder()
                .replyId(reply.getReplyId())
                .replierId(reply.getReplierId())
                .questionId(reply.getQuestionId())
                .procurementId(reply.getProcurementId())
                .reply(reply.getReply())
                .timeReplied(reply.getTimeReplied())
                .build();
    }
}
