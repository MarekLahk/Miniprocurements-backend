package ee.taltech.procurementSystemBackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id", nullable = false)
    private Integer announcementId;
    @Basic
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Basic
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;
    @Basic
    @Column(name = "announcement", nullable = false, length = -1)
    private String announcement;
    @Basic
    @Column(name = "date_added", nullable = false)
    private Timestamp dateAdded;

}
