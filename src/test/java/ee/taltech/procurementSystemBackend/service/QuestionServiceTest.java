package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.model.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.model.Question;
import ee.taltech.procurementSystemBackend.repository.QuestionRepository;
import ee.taltech.procurementSystemBackend.utils.QuestionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceTest {

    @MockBean
    private QuestionRepository repository;

    @Autowired
    private QuestionUtils utils;

    private QuestionService service;

    @BeforeEach
    void setUp() {
        service = new QuestionService(repository, utils);
    }

    @Test
    void ShouldGerQuestionById() {
        Optional<Question> optional = Optional.of(Question.builder()
                .questionId(1).question("test").build());
        when(repository.findById(1)).thenReturn(optional);
        when(repository.findById(2)).thenReturn(Optional.empty());

        QuestionDto expectedTrue = service.getQuestionByQuestionId(1);

        assertThat(expectedTrue.getQuestion()).isEqualTo("test");

        assertThatThrownBy(() -> service.getQuestionByQuestionId(2))
                .isInstanceOf(RequestedObjectNotFoundException.class)
                .hasMessageContaining("Question with id [2] does not exist");
    }
}
