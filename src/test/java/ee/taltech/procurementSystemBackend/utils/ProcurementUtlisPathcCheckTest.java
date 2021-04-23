package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class ProcurementUtlisPathcCheckTest {

    @MockBean
    private MiniprocurementRepository miniprocurementRepository;

    private ProcurementUtils procurementUtils;

    @BeforeEach
    void setUp() {
        procurementUtils = new ProcurementUtils(miniprocurementRepository);
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoDesc() {
        Miniprocurement procurement = new Miniprocurement();
        procurement.setRequirements("test");
        procurement.setDeadline(new Timestamp(System.currentTimeMillis()));
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Description must be present on status patch");
        procurement.setDescription("");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Description must not be blank");
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoReq() {
        Miniprocurement procurement = new Miniprocurement();
        procurement.setDescription("test");
        procurement.setDeadline(new Timestamp(System.currentTimeMillis()));
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Requirements must be present on status patch");
        procurement.setRequirements("");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Requirements must not be blank");
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoDeadline() {
        Miniprocurement procurement = new Miniprocurement();
        procurement.setRequirements("test");
        procurement.setDescription("test");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(MiniprocurementException.class)
                .hasMessageContaining("Deadline must be present on status patch");
    }

    @Test
    void checkProcurementBeforePatchShouldPass() {
        Miniprocurement procurement = new Miniprocurement();
        procurement.setRequirements("test");
        procurement.setDeadline(new Timestamp(System.currentTimeMillis()));
        procurement.setDescription("test");
        try {
            procurementUtils.checkProcurementBeforeStatusPatch(procurement);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
