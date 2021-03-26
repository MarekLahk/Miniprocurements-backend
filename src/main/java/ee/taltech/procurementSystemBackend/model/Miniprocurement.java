package ee.taltech.procurementSystemBackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Miniprocurement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Miniprocurement {

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
