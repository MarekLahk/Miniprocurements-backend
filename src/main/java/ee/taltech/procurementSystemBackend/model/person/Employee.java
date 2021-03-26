package ee.taltech.procurementSystemBackend.model.person;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Employee")
public class Employee extends Person {
}
