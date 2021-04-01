package ee.taltech.procurementSystemBackend.model.search;

import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


public abstract class SearchObject<T extends Person> {

    private Integer limit;
    private Integer page;
    private String sort;
    private Enum<Sort.Direction> dir;

    private Pageable getPageRequest() {
        return PageRequest.of(
                (page != null) ? page: 0,
                (limit != null && limit >= 0) ? limit: 10,
                (dir == Sort.Direction.DESC) ? Sort.by(sort).descending(): Sort.by(sort).ascending());
    }
    public SearchSpecPack<T> getSearchSpecPack() {
        SearchSpecPack<T> specPack = new SearchSpecPack<T>(
                Specification.where(null),
                getPageRequest());
//        specPack.getSpecification()

        return specPack;
    }

}
