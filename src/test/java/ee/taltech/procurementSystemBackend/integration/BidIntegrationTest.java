package ee.taltech.procurementSystemBackend.integration;

import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementPublicDto;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurementPartnerRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import ee.taltech.procurementSystemBackend.repository.person.PartnerRepository;
import ee.taltech.procurementSystemBackend.service.BidService;
import ee.taltech.procurementSystemBackend.service.ProcurementService;
import ee.taltech.procurementSystemBackend.utils.BidUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BidIntegrationTest {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private ProcurementPartnerRepository procurementPartnerRepository;
    @Autowired
    private BidUtils bidUtils;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProcurementRepository procurementRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ProcurerRepository procurerRepository;

    @Autowired
    private ProcurementService procurementService;

    private BidService bidService;

    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setEMail("test@mail.test");
        employee.setName("test name");
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);

        Partner partner = new Partner();
        partner.setEMail("testpartner@mail.test");
        partner.setName("test partner");
        partner.setCreatedAt(LocalDateTime.now());
        partner.setRegNr(476528346L);
        partner.setPartnerInfo("Test info");
        partnerRepository.save(partner);

        LocalDateTime nowPlusMonth = LocalDateTime.now().plusMonths(1);
        Timestamp deadline = Timestamp.valueOf(nowPlusMonth);

        Procurement procurement = Procurement
                .builder()
                .name("Procurement")
                .amount(2000)
                .description("Test")
                .requirements("Requirements")
                .deadline(deadline)
                .hasContract(false)
                .status((short) 2)
                .createdById(1)
                .build();
        procurement.setCreatedAt(LocalDateTime.now());
        procurementRepository.save(procurement);

        ProcurementPartner procurementPartner = new ProcurementPartner();
        procurementPartner.setProcurementId(1);
        procurementPartner.setPartnerId(2);

        procurementPartnerRepository.save(procurementPartner);
        bidService = new BidService(bidRepository, procurementPartnerRepository, bidUtils);
    }

    @AfterEach
    void tearDown() {
        bidRepository.deleteAll();
        procurementPartnerRepository.deleteAll();
        procurerRepository.deleteAll();
        procurementRepository.deleteAll();
        partnerRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void bidOperationsTest() {
        UUID link = procurementPartnerRepository.findById(1).get().getLinkId();
        System.out.println(link);

        BidDto dto = new BidDto();
        dto.setValue(1234L);
        dto.setDescription("Description");

        // add bid
        BidDto result = bidService.addBid(link, dto);
        assertThat(result.getDescription()).isEqualTo("Description");

        // get bid info
        ProcurementPublicDto searchResult = procurementService.getProcurementInfo(link);
        assertThat(searchResult.getName()).isEqualTo("Procurement");

        // update bid
        dto.setDescription("Updated");
        dto.setId(1);
        dto.setValue(123L);
        BidDto resultUpdated = bidService.updateBid(link, dto);
        System.out.println("Result: " + resultUpdated);
        assertThat(resultUpdated.getDescription()).isEqualTo("Updated");

        // patch bid
        BidDto patchDto = new BidDto();
        patchDto.setId(1);
        BidDto resultPatched = bidService.patchBidStatus(link, dto);
        assertThat(resultPatched.getStatus()).isEqualTo(2);
    }
}
