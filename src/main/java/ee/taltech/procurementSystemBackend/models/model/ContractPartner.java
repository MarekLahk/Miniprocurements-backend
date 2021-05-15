package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Contractpartners")
@JGlobalMap
@Getter
@Setter
public class ContractPartner extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_partner_id")
    private Integer id;
    @Basic
    @Column(name = "contract_id", nullable = false, length = -1)
    private Integer ContractId;
    @Basic
    @Column(name = "partner_id", nullable = false, length = -1)
    private Integer PartnerId;
}
