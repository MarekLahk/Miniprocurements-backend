package ee.taltech.procurementSystemBackend.models.model.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Partner")
@PrimaryKeyJoinColumn(name = "partner_id")
@JGlobalMap
public class Partner extends Person {

    @Column(name = "reg_nr")
    Long regNr;
    @Column(name = "partner_info")
    String partnerInfo;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ProcurementPartner> procurementPartners;


}
