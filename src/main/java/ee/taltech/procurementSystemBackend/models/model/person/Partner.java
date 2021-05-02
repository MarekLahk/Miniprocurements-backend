package ee.taltech.procurementSystemBackend.models.model.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


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


}
