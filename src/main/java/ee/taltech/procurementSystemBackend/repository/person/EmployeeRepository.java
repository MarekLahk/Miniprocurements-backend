package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PersonRepositoryInterface<Employee> {

    //Optional<Employee> findByEMail(String email);

//    @Modifying
//    @Query(value = "insert into employee (employee_id) VALUES (:id)", nativeQuery = true)
//    @Transactional
//    void addEmployee(Integer id);
}
