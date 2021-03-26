package ee.taltech.procurementSystemBackend.model.person;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Partner")
public class Partner extends Person {

    @Basic
    @Column(name = "reg_nr", nullable = true)
    Long regNr;
    @Basic
    @Column(name = "partner_info")
    String partnerInfo;

}
