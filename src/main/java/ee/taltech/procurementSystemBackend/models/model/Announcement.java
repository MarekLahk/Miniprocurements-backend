package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Announcement extends ModelBase {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Miniprocurement miniprocurement;
}
