package ee.taltech.procurementSystemBackend.model;

import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.jpa.domain.Specification;

public interface SearchObject<T extends Person> {

    public Specification<T> generateMatchers();

}
