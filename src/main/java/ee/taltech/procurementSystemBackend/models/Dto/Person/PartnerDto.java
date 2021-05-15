package ee.taltech.procurementSystemBackend.models.Dto.Person;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Schema(name = "PartnerDto", description = "DTO for partner")
public class PartnerDto extends PersonDto {
    @Schema(title="Reg number")
    private Long regNr;
    @Schema(title="Info about the partner")
    private String partnerInfo;

}
