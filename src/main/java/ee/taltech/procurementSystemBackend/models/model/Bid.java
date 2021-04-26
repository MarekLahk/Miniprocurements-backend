package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Bid extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false)
    private Integer bidId;
    @Basic
    @Column(name = "bidder_link_id", nullable = false)
    private UUID bidderLinkId;
    @Basic
    @Column(name = "bid_value", nullable = false)
    private Long bidValue;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @Basic
    @Column(name = "time_of_register", nullable = false)
    private Timestamp timeOfRegister;
    @Column(name = "bid_status")
    private Integer bidStatus;
    @Column(name = "procurement_id")
    private Integer procurementId;
}
