package ee.taltech.procurementSystemBackend.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class BidStatus {
    @Id@Column(name = "status_id", nullable = false)
    private Short statusId;


}
