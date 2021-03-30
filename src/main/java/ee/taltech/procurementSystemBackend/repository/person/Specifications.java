package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;


// TODO: Make specifications independent of Person.
// TODO: Make Person extend Global specifications
public class Specifications {

    public static <T extends Person> Specification<T> specAfter(LocalDateTime after) {
        return ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("timeOfRegister"), after));
    }

    public static <T extends Person> Specification<T> specBefore(LocalDateTime before) {
        return ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("timeOfRegister"), before));
    }

//    public static <T extends Person> Specification<T> specGreater(Long number) {
//        return ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("timeOfRegister"), number));
//    }
//
//    public static <T extends Person> Specification<T> specLess(Long number) {
//        return ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("timeOfRegister"), number));
//    }

    public static <T extends Person> Specification<T> specEquals(Long number) {
        return ((root, query, builder) -> builder.equal(root.get("regNr"), number));
    }



}
