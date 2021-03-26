package ee.taltech.procurementSystemBackend.model.person;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Data
//@Table(name = "Person")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(
//        strategy = InheritanceType.JOINED
//)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer personID;
    @Column(name = "person_name", length=100, nullable = true)
    private String personName;
    @Column(name = "e_mail", length = 100, nullable = false)
    private String eMail;
    @CreationTimestamp
    @Column(name = "time_of_register", columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOfRegister;


}
