package ee.taltech.procurementSystemBackend.models.Dto.Person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JGlobalMap
@Schema(name = "PersonDto", description = "DTO for person")
public class PersonDto extends DtoBase {

    @Schema(title="ID of person")
    private Integer id;

    @Schema(title="Name of person")
    private String name;

    @Schema(title="eMail address")
    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    private String eMail;

    @Schema(title="reg number")
    @JsonInclude(Include.NON_NULL)
    private Long regNr;

    @Schema(title="Info about partner")
    @JsonInclude(Include.NON_NULL)
    private String partnerInfo;

}
