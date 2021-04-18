package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.models.model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends PersonRepositoryInterface<Person> {

    //Optional<Person> findByEMail(String email);

//    @Query("SELECT p FROM Person p WHERE p.eMail = :email")
//    Optional<Person> findByPersonEmail(String email);
}
