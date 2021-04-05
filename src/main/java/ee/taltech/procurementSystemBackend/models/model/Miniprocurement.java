package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Miniprocurement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Miniprocurement extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Basic
    @Column(name = "procurement_name", nullable = false, length = 50)
    private String procurementName;
    @Basic
    @Column(name = "amount")
    private Integer amount;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @Basic
    @Column(name = "requirements", length = -1)
    private String requirements;
    @Basic
    @Column(name = "contract_id")
    private Integer contractId;
    @Basic
    @Column(name = "time_added", nullable = false)
    private Timestamp timeAdded;
    @Basic
    @Column(name = "added_by", nullable = false)
    private Integer addedBy;
    @Basic
    @Column(name = "deadline", nullable = false)
    private Timestamp deadline;
    @Basic
    @Column(name = "status", nullable = false)
    private Short status;
    @Basic
    @Column(name = "time_finished")
    private Timestamp timeFinished;

}
