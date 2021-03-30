package ee.taltech.procurementSystemBackend.model.person.search;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specAfter;
import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specBefore;


@Data
@ToString
public class PersonSearch<T extends Person> implements SearchObject<T> {

    public PersonSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit) {
        this.before = before;
        this.after = after;
        this.name = name;
        this.limit = limit;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime before;
    private LocalDateTime after;
    private String name;
    private Integer limit;

    public void setBefore(String before) {
        this.before = LocalDateTime.parse(before, formatter);
    }

    public void setAfter(String after) {
        this.after = LocalDateTime.parse(after, formatter);
    }

    public Specification<T> generateMatchers() {
        Specification<T> spec = Specification.where(null);

        if (after != null) {
            spec.and(specAfter(this.after));
        }
        if (before != null) {
            spec.and(specBefore(this.before));
        }
        return spec;
    }

}
