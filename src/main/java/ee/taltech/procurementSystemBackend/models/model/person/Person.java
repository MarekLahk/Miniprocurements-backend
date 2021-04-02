package ee.taltech.procurementSystemBackend.models.model.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JGlobalMap
public class Person extends ModelBase {

    @Id()
    @GeneratedValue
    @Column(name = "person_id")
    Integer personID;

    @Column(name = "person_name", length=100)
    private String personName;

    @Column(name = "e_mail", length = 100, nullable = false)
    private String eMail;

    @CreationTimestamp@Column(name = "time_of_register", columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOfRegister;

    public Person(PersonDto personDto) {

        this.personID = personDto.getPersonID();
        this.personName = personDto.getPersonName();
        this.eMail = personDto.getEMail();
        this.timeOfRegister = personDto.getTimeOfRegister();

    }


}
