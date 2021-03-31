package ee.taltech.procurementSystemBackend.model.person.search;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Partner;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specEquals;

@EqualsAndHashCode(callSuper = true)
@Getter@Setter
public class PartnerSearch extends PersonSearch<Partner> implements SearchObject<Partner> {

    public PartnerSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit, Long regnr) {
        super(before, after, name, limit);
        this.regNr = regnr;
    }

    private Long regNr;

    @Override
    public Specification<Partner> generateMatchers() {
        Specification<Partner> spec = super.generateMatchers();
        if (regNr != null) {
            spec = spec.and(specEquals("regNr", regNr));
        }
        return spec;
    }

    @Override
    public String toString() {
        System.out.println("Here");
        return "PartnerSearch{" +
                "regNr=" + regNr +
                "} " + super.toString();
    }
}
