package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Data
public class ProcurementPartner extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "link_id")
    @Generated(GenerationTime.INSERT)
    private UUID linkId;
    @Basic
    @Column(name = "procurement_id", nullable = false, length = -1)
    private Integer procurementId;
    @Basic
    @Column(name = "partner_id", nullable = false, length = -1)
    private Integer partnerId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Procurement procurement;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "procurementPartner")
    private List<Bid> bids;
}
