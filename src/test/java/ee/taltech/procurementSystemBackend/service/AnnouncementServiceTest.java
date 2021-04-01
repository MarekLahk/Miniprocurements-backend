package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.model.Announcement;
import ee.taltech.procurementSystemBackend.model.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import ee.taltech.procurementSystemBackend.utils.AnnouncementUtils;
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
public class AnnouncementServiceTest {

    @MockBean
    private AnnouncementRepository repository;

    @Autowired
    private AnnouncementUtils utils;

    private AnnouncementService service;

    @BeforeEach
    void setUp() {
        service = new AnnouncementService(repository, utils);
    }

    @Test
    void shouldGetAnnouncementById() {
        Optional<Announcement> optional = Optional.of(Announcement.builder()
                .announcementId(1).announcement("test").build());
        when(repository.findById(1)).thenReturn(optional);
        when(repository.findById(2)).thenReturn(Optional.empty());

        AnnouncementDto expectedTrue = service.getAnnouncementById(1);

        assertThat(expectedTrue.getAnnouncement()).isEqualTo("test");

        assertThatThrownBy(() -> service.getAnnouncementById(2))
                .isInstanceOf(RequestedObjectNotFoundException.class)
                .hasMessageContaining("Announcement with id [2] does not exist");
    }
}
