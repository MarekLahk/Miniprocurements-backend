package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Procurer extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procurer_id", nullable = false)
    private Integer procurerId;
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;
}
