package ee.taltech.procurementSystemBackend.models.model.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
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
@JGlobalMap
public class Partner extends Person {

    @Column(name = "reg_nr")
    Long regNr;
    @Column(name = "partner_info")
    String partnerInfo;

    public Partner(PartnerDto partnerDto) {
        super(partnerDto);
        this.regNr = partnerDto.getRegNr();
        this.partnerInfo = partnerDto.getPartnerInfo();
    }

}
