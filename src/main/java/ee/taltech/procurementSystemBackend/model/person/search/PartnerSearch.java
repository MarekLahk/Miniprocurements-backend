package ee.taltech.procurementSystemBackend.model.person.search;

import ee.taltech.procurementSystemBackend.model.SearchObject;
import ee.taltech.procurementSystemBackend.model.person.Partner;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

import static ee.taltech.procurementSystemBackend.repository.person.Specifications.specEquals;

@EqualsAndHashCode(callSuper = true)
@Getter@Setter
public class PartnerSearch extends PersonSearch<Partner> implements SearchObject<Partner>{

    public PartnerSearch(LocalDateTime before, LocalDateTime after, String name, Integer limit, Long regNr) {
        super(before, after, name, limit);
        this.regNr = regNr;
    }

    private Long regNr;

    @Override
    public Specification<Partner> generateMatchers() {
        Specification<Partner> spec = super.generateMatchers();
        if (regNr != null) {
            spec.and(specEquals(regNr));
        }
        return spec;
    }

}
