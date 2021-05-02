package ee.taltech.procurementSystemBackend.models.model.person;


import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Employee")
@PrimaryKeyJoinColumn(name = "employee_id")
@JGlobalMap
public class Employee extends Person {


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
    private Set<Procurement> procurements;

}
