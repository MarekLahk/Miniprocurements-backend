package ee.taltech.procurementSystemBackend.model.search.person;

import ee.taltech.procurementSystemBackend.model.person.Partner;
import ee.taltech.procurementSystemBackend.model.search.SearchSpecPack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

import java.time.LocalDateTime;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specEquals;

@EqualsAndHashCode(callSuper = true)
@Getter@Setter
public class PartnerSearch extends PersonSearch<Partner> {

    public PartnerSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name, Long regNr) {
        super(limit, page, sort, dir, before, after, name);
        this.regNr = regNr;
    }

    private Long regNr;

    @Override
    public SearchSpecPack<Partner> getSearchSpec() {
        SearchSpecPack<Partner> spec = super.getSearchSpec();
        if (regNr != null) {
            spec.addSpec(specEquals("regNr", regNr));
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
