package ee.taltech.procurementSystemBackend.model.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

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

    public Person(String personName, String eMail) {
        this.personName = personName;
        this.eMail = eMail;
    }
}
