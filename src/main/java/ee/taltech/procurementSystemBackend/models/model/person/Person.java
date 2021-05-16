package ee.taltech.procurementSystemBackend.models.model.person;

import com.googlecode.jmapper.annotations.JGlobalMap;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    Integer id;

    @Column(name = "person_name", length=100)
    private String name;

    @Column(name = "e_mail", length = 100, nullable = false, unique = true)
    private String eMail;

    @CreationTimestamp@Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

}
