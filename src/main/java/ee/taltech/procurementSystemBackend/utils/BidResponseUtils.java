package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionAndRepliesResponse;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.mapper.ReplyMapper;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BidResponseUtils {

    private final QuestionMapper questionMapper;
    private final ReplyMapper replyMapper;
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;

    public BidResponseUtils(QuestionRepository questionRepository,
                            ReplyRepository replyRepository) {
        this.questionRepository = questionRepository;
        this.replyRepository = replyRepository;
        this.questionMapper = QuestionMapper.INSTANCE;
        this.replyMapper = ReplyMapper.INSTANCE;
    }

    public List<QuestionAndRepliesResponse> getQuestionsAndReplies(Integer procurementId) {
        List<QuestionAndRepliesResponse> result = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByProcurementId(procurementId);
        questions.forEach(question -> {
            QuestionDto questionDto = questionMapper.toDto(question);
            List<ReplyDto> replies = replyRepository
                    .findAllByQuestionId(question.getId())
                    .stream()
                    .map(replyMapper::toDto)
                    .collect(Collectors.toList());
            result.add(new QuestionAndRepliesResponse(questionDto, replies));
        });
        return result;
    }
}
