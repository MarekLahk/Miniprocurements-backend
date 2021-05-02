package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
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
    private PocurementRepository pocurementRepository;

    private ProcurementUtils procurementUtils;

    @BeforeEach
    void setUp() {
        procurementUtils = new ProcurementUtils(pocurementRepository);
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoDesc() {
        Procurement procurement = new Procurement();
        procurement.setRequirements("test");
        procurement.setDeadline(new Timestamp(System.currentTimeMillis()));
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(ProcurementException.class)
                .hasMessageContaining("Description must be present on status patch");
        procurement.setDescription("");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(ProcurementException.class)
                .hasMessageContaining("Description must not be blank");
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoReq() {
        Procurement procurement = new Procurement();
        procurement.setDescription("test");
        procurement.setDeadline(new Timestamp(System.currentTimeMillis()));
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(ProcurementException.class)
                .hasMessageContaining("Requirements must be present on status patch");
        procurement.setRequirements("");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(ProcurementException.class)
                .hasMessageContaining("Requirements must not be blank");
    }

    @Test
    void checkProcurementBeforePatchShouldThrowExceptionNoDeadline() {
        Procurement procurement = new Procurement();
        procurement.setRequirements("test");
        procurement.setDescription("test");
        assertThatThrownBy(() -> procurementUtils.checkProcurementBeforeStatusPatch(procurement))
                .isInstanceOf(ProcurementException.class)
                .hasMessageContaining("Deadline must be present on status patch");
    }

    @Test
    void checkProcurementBeforePatchShouldPass() {
        Procurement procurement = new Procurement();
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
