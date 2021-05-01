package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Contract")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Contract extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false)
    private Integer contractId;
    @Basic
    @Column(name = "contract_name", nullable = false, length = 50)
    private String contractName;
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
    @Column(name = "contract_sub_id")
    private Integer contractSubId;
    @Basic
    @Column(name = "has_contract")
    private Boolean hasContract;
    @Basic
    @Column(name = "time_added", nullable = false)
    private Timestamp timeAdded;
    @Basic
    @Column(name = "added_by", nullable = false)
    private Integer addedBy;
    @Basic
    @Column(name = "deadline")
    private Timestamp deadline;
    @Basic
    @Column(name = "status", nullable = false)
    private Short status;
    @Basic
    @Column(name = "time_finished")
    private Timestamp timeFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", insertable = false, updatable = false)
    private Employee employee;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private Set<Announcement> announcements;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private Set<Bid> bids;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private Set<ContractPartner> contractPartners;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private Set<Question> questions;



}
