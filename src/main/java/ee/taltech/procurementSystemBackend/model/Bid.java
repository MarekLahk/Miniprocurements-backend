package ee.taltech.procurementSystemBackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Bid {
    @Id@Column(name = "bid_id", nullable = false)
    private Integer bidId;
    @Basic@Column(name = "bid_value", nullable = false)
    private Long bidValue;
    @Basic@Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic@Column(name = "time_of_register", nullable = false)
    private Timestamp timeOfRegister;
    @Column(name = "bid_status")
    private Integer bidStatus;

}
