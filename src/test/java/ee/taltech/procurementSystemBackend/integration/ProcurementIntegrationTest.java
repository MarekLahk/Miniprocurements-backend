package ee.taltech.procurementSystemBackend.integration;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import ee.taltech.procurementSystemBackend.service.ProcurementService;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProcurementIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PocurementRepository pocurementRepository;

    @Autowired
    private ProcurerRepository procurerRepository;

    @Autowired
    private RepositoryInterface<Procurement> repository;

    @MockBean
    private AuthUtils authUtils;

    @Autowired
    private ProcurementUtils procurementUtils;

    private ProcurementService procurementService;

    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setEMail("test@mail.com");
        employee.setName("test name");
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);

        procurementService = new ProcurementService(repository, pocurementRepository, authUtils, procurementUtils);
    }

    @AfterEach
    void tearDown() {
        procurerRepository.deleteAll();
        pocurementRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void procurementOperationsTest() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Person person = new Person();
        person.setId(1);
        when(authUtils.getPersonToPerformOperations(authentication))
                .thenReturn(person);

        LocalDateTime nowPlusMonth = LocalDateTime.now().plusMonths(1);
        Timestamp deadline = Timestamp.valueOf(nowPlusMonth);

        ProcurementDto dto = ProcurementDto
                .builder()
                .name("Procurement")
                .amount(2000)
                .description("Test")
                .requirements("Requirements")
                .deadline(deadline).build();

        Optional<ProcurementDto> result = Optional.of(procurementService.addProcurement(dto, authentication));

        assertThat(result.get().getCreatedById()).isEqualTo(1);
    }
}
