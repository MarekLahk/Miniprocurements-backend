package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("{id}")
    public QuestionDto getQuestionByQuestionId(@PathVariable Integer id) {
        return questionService.getQuestionByQuestionId(id);
    }

    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("procurement/{procurementId}")
    public List<QuestionDto> findAllByProcurementId(@PathVariable Integer procurementId) {
        return questionService.findAllByProcurementId(procurementId);
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
