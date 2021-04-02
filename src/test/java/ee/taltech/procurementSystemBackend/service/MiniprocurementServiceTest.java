package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
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
public class MiniprocurementServiceTest {

    @MockBean
    private MiniprocurementRepository repository;

    @Autowired
    private ProcurementUtils procurementUtils;

    private MiniprocurementService service;

    @BeforeEach
    void setUp() {
        service = new MiniprocurementService(repository, procurementUtils);
    }

    @Test
    void shouldGetProcurement() {
        Optional<Miniprocurement> optional = Optional.of(Miniprocurement.builder()
                .procurementId(1).procurementName("test").build());
        when(repository.findById(1)).thenReturn(optional);
        when(repository.findById(2)).thenReturn(Optional.empty());

        MiniProcurementDto expectedTrue = service.getMiniprocurementById(1);

        assertThat(expectedTrue.getProcurementName()).isEqualTo("test");

        assertThatThrownBy(() -> service.getMiniprocurementById(2))
                .isInstanceOf(RequestedObjectNotFoundException.class)
                .hasMessageContaining("Procurement with id [2] does not exist");
    }
}
