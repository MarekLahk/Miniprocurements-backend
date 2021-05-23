package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Data
@DynamicInsert
public class Bid extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "link_id", nullable = false)
    private UUID linkId;
    @Basic
    @Column(name = "procurement_partner_id")
    private Integer procurementPartnerId;
    @Basic
    @Column(name = "value", nullable = false)
    private Long value;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @Column(name = "status")
    private Integer status;
    @Column(name = "procurement_id")
    private Integer procurementId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Procurement procurement;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procurement_partner_id", insertable = false, updatable = false)
    private ProcurementPartner procurementPartner;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bid")
    private Set<Document> documents;

}
