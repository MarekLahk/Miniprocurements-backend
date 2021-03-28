package ee.taltech.procurementSystemBackend.model.person;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employee")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {
    public Employee(String personName, String eMail) {
        super(personName, eMail);
    }


    //    @Column(name = "person_id", insertable=false, updatable = false)
//    Integer personID;
}
