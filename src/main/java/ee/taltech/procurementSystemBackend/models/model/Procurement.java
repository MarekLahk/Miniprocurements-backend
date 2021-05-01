package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "procurement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Procurement extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procurement_id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "procurement_name", nullable = false, length = 50)
    private String name;
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
    @Column(name = "contract_sub_id")
    private Integer contractSubId;
    @Basic
    @Column(name = "has_contract")
    private Boolean hasContract;
    @Basic
    @Column(name = "created_by", nullable = false)
    private Integer createdById;
    @Basic
    @Column(name = "deadline")
    private Timestamp deadline;
    @Basic
    @Column(name = "status", nullable = false)
    private Short status;
    @Basic
    @Column(name = "completion_deadline")
    private Timestamp completionDeadline;
    @Basic
    @Column(name = "completion_deadline_days")
    private Timestamp completionDeadlineDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private Employee createdBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procurement")
    private Set<Announcement> announcements;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procurement")
    private Set<Bid> bids;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procurement")
    private Set<ProcurementPartner> procurementPartners;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procurement")
    private Set<Question> questions;



}
