package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.model.Reply;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import ee.taltech.procurementSystemBackend.utils.ReplyUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyUtils replyUtils;

    public ReplyDto getReplyByReplyId(Integer id) {
        return replyUtils.convertFromReplyToDto(
                replyRepository.findByReplyId(id)
        );
    }

    public List<ReplyDto> getAllReplies() {
        return replyRepository.findAll().stream()
                .map(replyUtils::convertFromReplyToDto)
                .collect(Collectors.toList());
    }

    public List<ReplyDto> getRepliesByQuestion(Integer questionId) {
        return replyRepository.findAllByQuestionId(questionId).stream()
                .map(replyUtils::convertFromReplyToDto)
                .collect(Collectors.toList());
    }

    public List<ReplyDto> getRepliesByProcurement(Integer procurementId) {
        return replyRepository.findAllByProcurementId(procurementId).stream()
                .map(replyUtils::convertFromReplyToDto)
                .collect(Collectors.toList());
    }

    public ReplyDto addReply(ReplyDto dto) {
        Reply reply = replyUtils.convertFromDtoToReply(dto);
        return replyUtils.convertFromReplyToDto(
                replyRepository.save(reply)
        );
    }
}
