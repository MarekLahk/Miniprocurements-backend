package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Announcement getAnnouncementByAnnouncementId(Integer id);

    List<Announcement> findAllByProcurementId(Integer id);
}
