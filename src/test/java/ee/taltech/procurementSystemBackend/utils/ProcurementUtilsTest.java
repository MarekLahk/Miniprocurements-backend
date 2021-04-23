package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class ProcurementUtilsTest {

    @MockBean
    private MiniprocurementRepository miniprocurementRepository;
    
    private ProcurementUtils procurementUtils;

    @BeforeEach
    void setUp() {
        procurementUtils = new ProcurementUtils(miniprocurementRepository);
    }

    @Test
    void checksDeadlineIsNotInPastShouldThrowException() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        assertThatThrownBy(() -> procurementUtils.checkProcurementDeadlineIsNotInPast(timestamp))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Deadline cannot be before now.");
    }

    @Test
    void checksDeadlineIsNotInPastShouldPass() {
        LocalDateTime nowPlusDay = LocalDateTime.now().plusDays(1);
        Timestamp timestamp = Timestamp.valueOf(nowPlusDay);
        try {
            procurementUtils.checkProcurementDeadlineIsNotInPast(timestamp);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
