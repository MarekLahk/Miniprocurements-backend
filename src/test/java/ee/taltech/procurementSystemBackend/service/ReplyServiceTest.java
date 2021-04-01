package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.model.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.model.Reply;
import ee.taltech.procurementSystemBackend.repository.ReplyRepository;
import ee.taltech.procurementSystemBackend.utils.ReplyUtils;
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
public class ReplyServiceTest {

    @MockBean
    private ReplyRepository repository;

    @Autowired
    private ReplyUtils utils;

    private ReplyService service;

    @BeforeEach
    void setUp() {
        service = new ReplyService(repository, utils);
    }

    @Test
    void shouldGetReplyById() {
        Optional<Reply> optional = Optional.of(Reply.builder()
                .replyId(1).reply("test").build());
        when(repository.findById(1)).thenReturn(optional);
        when(repository.findById(2)).thenReturn(Optional.empty());

        ReplyDto expectedTrue = service.getReplyByReplyId(1);

        assertThat(expectedTrue.getReply()).isEqualTo("test");

        assertThatThrownBy(() -> service.getReplyByReplyId(2))
                .isInstanceOf(RequestedObjectNotFoundException.class)
                .hasMessageContaining("Reply with id [2] does not exist");
    }
}
