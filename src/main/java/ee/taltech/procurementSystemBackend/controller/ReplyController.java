package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.model.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/replies")
@AllArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("{id}")
    public ReplyDto getReplyByReplyId(@PathVariable Integer id) {
        return replyService.getReplyByReplyId(id);
    }

    @GetMapping
    public List<ReplyDto> getAllReplies() {
        return replyService.getAllReplies();
    }

    @GetMapping("question/{questionId}")
    public List<ReplyDto> getRepliesByQuestion(@PathVariable Integer questionId) {
        return replyService.getRepliesByQuestion(questionId);
    }

    @GetMapping("procurement/{procurementId}")
    public List<ReplyDto> getRepliesByProcurement(@PathVariable Integer procurementId) {
        return replyService.getRepliesByProcurement(procurementId);
    }

    @PostMapping
    public ReplyDto addReply(@RequestBody ReplyDto dto) {
        return replyService.addReply(dto);
    }
}
