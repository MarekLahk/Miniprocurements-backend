package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Announcement;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnouncementRepository extends RepositoryInterface<Announcement> {

    Optional<Announcement> findByIdAndEmployeeId(Integer announcementId, Integer employeeId);
}
