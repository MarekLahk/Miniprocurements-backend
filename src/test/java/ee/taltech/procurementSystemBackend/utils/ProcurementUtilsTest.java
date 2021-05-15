package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
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
    private ProcurerRepository procurerRepository;
    
    private ProcurementUtils procurementUtils;

    @BeforeEach
    void setUp() {
        procurementUtils = new ProcurementUtils(procurerRepository);
    }

    @Test
    void checksDeadlineIsNotInPastShouldThrowException() {
        LocalDateTime nowMinusDay = LocalDateTime.now().minusDays(1);
        Timestamp timestamp = Timestamp.valueOf(nowMinusDay);
        assertThatThrownBy(() -> procurementUtils.checkProcurementDeadlineIsNotInPast(timestamp))
                .isInstanceOf(ProcurementException.class)
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
