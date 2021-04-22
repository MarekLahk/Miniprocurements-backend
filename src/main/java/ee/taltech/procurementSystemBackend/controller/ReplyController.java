package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import ee.taltech.procurementSystemBackend.models.search.ReplySearch;
import ee.taltech.procurementSystemBackend.service.ReplyService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/replies")
public class ReplyController extends ControllerBase<Reply, ReplyDto, ReplySearch> {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        super(replyService, Reply.class, ReplyDto.class);
        this.replyService = replyService;
    }

    @PostMapping
    public ReplyDto addReply(@Valid @RequestBody ReplyDto dto, Authentication authentication) {
        return replyService.addReply(dto, authentication);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteReply(@PathVariable Integer id) {
        replyService.deleteReply(id);
    }
}
