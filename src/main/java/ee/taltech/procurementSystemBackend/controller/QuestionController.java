package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.models.search.QuestionSearch;
import ee.taltech.procurementSystemBackend.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/questions")
public class QuestionController extends ControllerBase<Question, QuestionDto, QuestionSearch> {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        super(questionService, Question.class, QuestionDto.class);
        this.questionService = questionService;
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
