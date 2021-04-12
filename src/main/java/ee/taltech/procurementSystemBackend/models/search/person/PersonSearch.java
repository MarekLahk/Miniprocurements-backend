package ee.taltech.procurementSystemBackend.models.search.person;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.models.search.SearchSpecPack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

import java.time.LocalDateTime;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.*;
import static ee.taltech.procurementSystemBackend.utils.Statics.formatter;


@EqualsAndHashCode(callSuper = true)
@Getter@Setter
public class PersonSearch<T extends Person> extends SearchObject<T> {

    public PersonSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name) {
        super(limit, page, sort, dir);
        this.before = before;
        this.after = after;
        this.name = name;
    }


    private LocalDateTime before;
    private LocalDateTime after;
    private String name;

    public void setBefore(String before) {
        this.before = LocalDateTime.parse(before, formatter);
    }

    public void setAfter(String after) {
        this.after = LocalDateTime.parse(after, formatter);
    }

    public SearchSpecPack<T> getSearchSpec() {
        SearchSpecPack<T> specPack = super.getSearchSpec();
        if (after != null) {
            specPack.addSpec(specAfter("timeOfRegister", this.after));
        }
        if (before != null) {
            specPack.addSpec(specBefore("timeOfRegister", this.before));
        }
        if (name != null) {
            specPack.addSpec(specEquals("personName", this.name));
        }
        return specPack;
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
