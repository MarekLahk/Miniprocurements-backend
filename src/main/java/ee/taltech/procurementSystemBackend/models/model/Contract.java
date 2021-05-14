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
import java.time.LocalDateTime;
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
    private Integer id;
    @Basic
    @Column(name = "contract_reference_number", nullable = false)
    private Integer referenceNumber;
    @Basic
    @Column(name = "procurement_template_id", nullable = false)
    private Integer procurementTemplateId;
    @Basic
    @Column(name = "bid_template_id", nullable = false)
    private Integer bidTemplateId;
    @Basic
    @Column(name = "contract_name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;
}
