package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnnouncmentService {

    private final AnnouncementRepository announcementRepository;
}
