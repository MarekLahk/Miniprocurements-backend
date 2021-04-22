package ee.taltech.procurementSystemBackend.repository.person;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;



public class Specifications {

    public static <T> Specification<T> specAfter(String columnName,LocalDateTime after) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(columnName), after);
    }

    public static <T> Specification<T> specBefore(String columnName, LocalDateTime before) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(columnName), before);
    }

//    public static <T extends Person> Specification<T> specGreater(Long number) {
//        return ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("timeOfRegister"), number));
//    }
//
//    public static <T extends Person> Specification<T> specLess(Long number) {
//        return ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("timeOfRegister"), number));
//    }

    public static <T> Specification<T> specEquals(String columnName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }





}
