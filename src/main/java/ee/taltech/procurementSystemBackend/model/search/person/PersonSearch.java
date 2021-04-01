package ee.taltech.procurementSystemBackend.model.search.person;

import ee.taltech.procurementSystemBackend.model.search.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Person;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.*;



@EqualsAndHashCode(callSuper = true)
@Getter@Setter
public class PersonSearch<T extends Person> extends SearchObject<T> {

    public PersonSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit) {
        this.before = before;
        this.after = after;
        this.name = name;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime before;
    private LocalDateTime after;
    private String name;

    public void setBefore(String before) {
        this.before = LocalDateTime.parse(before, formatter);
    }

    public void setAfter(String after) {
        this.after = LocalDateTime.parse(after, formatter);
    }

    public Specification<T> getSearchSpecPack() {
        Specification<T> spec = Specification.where(null);
        if (after != null) {
            spec = spec.and(specAfter(this.after));
        }
        if (before != null) {
            spec = spec.and(specBefore(this.before));
        }
        if (name != null) {
            spec = spec.and(specEquals("personName", this.name));
        }
        System.out.println(spec);
        return spec;
    }

    @Override
    public String toString() {
        return "PersonSearch{" +
                "before=" + before +
                ", after=" + after +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
