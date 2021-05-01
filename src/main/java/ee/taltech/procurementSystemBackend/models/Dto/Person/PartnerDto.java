package ee.taltech.procurementSystemBackend.models.Dto.Person;

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

}
