package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import com.googlecode.jmapper.annotations.JGlobalMap;
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
public class MiniprocurementPartner extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "miniprocurementpartner_id", nullable = false)
    private Integer miniprocurementpartnerId;
    @Basic
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Basic
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;
    @Basic
    @Column(name = "miniprocurementpartner", nullable = false, length = -1)
    private String miniprocurementpartner;
    @Basic
    @Column(name = "date_added", nullable = false)
    private Timestamp dateAdded;
}