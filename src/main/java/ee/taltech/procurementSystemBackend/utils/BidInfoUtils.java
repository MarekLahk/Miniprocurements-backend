package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.models.mapper.QuestionMapper;
import ee.taltech.procurementSystemBackend.models.mapper.ReplyMapper;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import org.springframework.stereotype.Component;

@Component
public class BidInfoUtils {

    private final QuestionMapper questionMapper;
    private final ReplyMapper replyMapper;
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;

    public BidInfoUtils(QuestionRepository questionRepository,
                            ReplyRepository replyRepository) {
        this.questionRepository = questionRepository;
        this.replyRepository = replyRepository;
        this.questionMapper = QuestionMapper.INSTANCE;
        this.replyMapper = ReplyMapper.INSTANCE;
    }

//    public List<QuestionAndRepliesResponse> getQuestionsAndReplies(Integer procurementId) {
//        List<QuestionAndRepliesResponse> result = new ArrayList<>();
//        List<Question> questions = questionRepository.findAllByProcurementId(procurementId);
//        questions.forEach(question -> {
//            QuestionDto questionDto = questionMapper.toDto(question);
//            List<ReplyDto> replies = replyRepository
//                    .findAllByQuestionId(question.getId())
//                    .stream()
//                    .map(replyMapper::toDto)
//                    .collect(Collectors.toList());
//            result.add(new QuestionAndRepliesResponse(questionDto, replies));
//        });
//        return result;
//    }
}
