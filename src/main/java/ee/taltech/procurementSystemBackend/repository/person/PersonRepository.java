package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends PersonRepositoryInterface<Person> {

}
