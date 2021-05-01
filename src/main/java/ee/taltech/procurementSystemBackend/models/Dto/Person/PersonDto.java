package ee.taltech.procurementSystemBackend.models.Dto.Person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JGlobalMap
public class PersonDto extends DtoBase {

    private Integer id;
    private String name;
    @NotBlank(message = "Email cannot be blank")
    private String eMail;
    @JsonInclude(Include.NON_NULL)
    private Long regNr;
    @JsonInclude(Include.NON_NULL)
    private String partnerInfo;

}
