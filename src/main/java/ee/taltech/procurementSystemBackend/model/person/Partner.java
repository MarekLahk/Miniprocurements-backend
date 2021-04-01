package ee.taltech.procurementSystemBackend.model.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "Partner")
@PrimaryKeyJoinColumn(name = "partner_id")
public class Partner extends Person {

    @Column(name = "reg_nr")
    Long regNr;
    @Column(name = "partner_info")
    String partnerInfo;

    public Partner(String personName, String eMail, Long regNr, String partnerInfo) {
        super(personName, eMail);
        this.regNr = regNr;
        this.partnerInfo = partnerInfo;
    }
}
