package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/public/questions")
@AllArgsConstructor
public class QuestionPublicController {

    private final QuestionService questionService;

    @PostMapping("{bidderLinkId}")
    public QuestionDto addQuestion(@PathVariable UUID bidderLinkId,
                                   @Valid @RequestBody QuestionDto dto) {
        return questionService.addQuestion(bidderLinkId, dto);
    }
}
