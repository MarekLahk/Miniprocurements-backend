package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
