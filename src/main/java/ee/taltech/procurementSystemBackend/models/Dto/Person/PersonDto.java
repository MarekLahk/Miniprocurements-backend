package ee.taltech.procurementSystemBackend.models.Dto.Person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JGlobalMap
public class PersonDto extends DtoBase {

    private Integer personID;
    private String personName;
    private String eMail;
    private LocalDateTime timeOfRegister;
    @JsonInclude(Include.NON_NULL)
    private Long regNr;
    @JsonInclude(Include.NON_NULL)
    private String partnerInfo;

    public PersonDto(Person person) {

        this.personID = person.getPersonID();
        this.personName = person.getPersonName();
        this.eMail = person.getEMail();
        this.timeOfRegister = person.getTimeOfRegister();

    }

}
