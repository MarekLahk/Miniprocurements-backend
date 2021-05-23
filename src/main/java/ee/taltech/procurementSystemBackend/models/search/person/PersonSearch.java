package ee.taltech.procurementSystemBackend.models.search.person;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.models.search.SearchSpecPack;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specEquals;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PersonSearch<T extends Person> extends SearchObject<T> {

//    public PersonSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String name) {
//        super(limit, page, sort, dir);
//        this.name = name;
//    }


    private String name;

//    public void setBefore(String before) {
//        this.before = LocalDateTime.parse(before, formatter);
//    }
//
//    public void setAfter(String after) {
//        this.after = LocalDateTime.parse(after, formatter);
//    }

    public SearchSpecPack<T> getSearchSpec() {
        SearchSpecPack<T> specPack = super.getSearchSpec();
//        if (after != null) {
//            specPack.addSpec(specAfter("timeOfRegister", this.after));
//        }
//        if (before != null) {
//            specPack.addSpec(specBefore("timeOfRegister", this.before));
//        }
        if (name != null) {
            specPack.addSpec(specEquals("name", this.name));
        }
        return specPack;
    }

    @Override
    public String toString() {
        return "PersonSearch{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
