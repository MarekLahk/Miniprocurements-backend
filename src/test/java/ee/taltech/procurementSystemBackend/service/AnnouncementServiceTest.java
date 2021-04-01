package ee.taltech.procurementSystemBackend.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnnouncementServiceTest {

//    @MockBean
//    private AnnouncementRepository repository;
//
//    @Autowired
//    private AnnouncementUtils utils;
//
//    private AnnouncementService service;
//
//    @BeforeEach
//    void setUp() {
//        service = new AnnouncementService(repository, utils);
//    }
//
//    @Test
//    void shouldGetAnnouncementById() {
//        Optional<Announcement> optional = Optional.of(Announcement.builder()
//                .announcementId(1).announcement("test").build());
//        when(repository.findById(1)).thenReturn(optional);
//        when(repository.findById(2)).thenReturn(Optional.empty());
//
//        AnnouncementDto expectedTrue = service.getAnnouncementById(1);
//
//        assertThat(expectedTrue.getAnnouncement()).isEqualTo("test");
//
//        assertThatThrownBy(() -> service.getAnnouncementById(2))
//                .isInstanceOf(RequestedObjectNotFoundException.class)
//                .hasMessageContaining("Announcement with id [2] does not exist");
//    }
}
