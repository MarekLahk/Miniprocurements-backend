package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Bid extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "bidder_link_id", nullable = false)
    private UUID linkId;
    @Basic
    @Column(name = "bid_value", nullable = false)
    private Long bidValue;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @Column(name = "bid_status")
    private Integer bidStatus;
    @Column(name = "procurement_id")
    private Integer procurementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Procurement procurement;

}
