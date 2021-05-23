package ee.taltech.procurementSystemBackend.models.search.person;

import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.models.search.SearchSpecPack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specEquals;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class PartnerSearch extends PersonSearch<Partner> {

//    public PartnerSearch(Integer limit, Integer page, String sort, Direction dir, LocalDateTime before, LocalDateTime after, String name, Long regNr) {
//        super(limit, page, sort, dir, name);
//        this.regNr = regNr;
//    }

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
