package ee.taltech.procurementSystemBackend.models.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class BidStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Short statusId;


}
