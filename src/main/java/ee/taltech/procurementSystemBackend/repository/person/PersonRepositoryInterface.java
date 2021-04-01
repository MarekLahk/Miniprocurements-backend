package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonRepositoryInterface<T extends Person> extends RepositoryInterface<T> {


}
