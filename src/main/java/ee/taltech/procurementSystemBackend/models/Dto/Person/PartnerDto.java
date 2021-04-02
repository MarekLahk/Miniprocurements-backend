package ee.taltech.procurementSystemBackend.models.Dto.Person;

import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto extends PersonDto {

    private Long regNr;
    private String partnerInfo;

    public PartnerDto(Partner partner) {
        super(partner);
        this.regNr = partner.getRegNr();
        this.partnerInfo = partner.getPartnerInfo();
    }
}
