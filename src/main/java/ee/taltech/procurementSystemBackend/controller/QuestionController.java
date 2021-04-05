package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.models.search.QuestionSearch;
import ee.taltech.procurementSystemBackend.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/question")
public class QuestionController extends ControllerBase<Question, QuestionDto, QuestionSearch> {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        super(questionService, Question.class, QuestionDto.class);
        this.questionService = questionService;
    }

    @PostMapping
    public QuestionDto addQuestion(@RequestBody QuestionDto dto) {
        return questionService.addQuestion(dto);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
