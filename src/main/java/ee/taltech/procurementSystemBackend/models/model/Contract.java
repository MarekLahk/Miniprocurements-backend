package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = "contract_name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;
    @Basic
    @Column(name = "description")
    private String description;
}
