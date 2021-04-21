package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class MiniprocurementPartner extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "miniprocurementpartner_id", nullable = false)
    private Integer miniprocurementpartnerId;
    @Basic
    @Column(name = "miniprocurementpartnername", nullable = false, length = -1)
    private String miniprocurementpartnername;
    @Basic
    @Column(name = "miniprocurementpartneremail", nullable = false, length = -1)
    private String miniprocurementpartneremail;
}