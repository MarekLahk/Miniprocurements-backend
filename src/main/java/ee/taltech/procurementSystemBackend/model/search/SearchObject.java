package ee.taltech.procurementSystemBackend.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
public abstract class SearchObject<T> {

    private Integer limit;
    private Integer page;
    private String sort;
    private Sort.Direction dir;

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Sort getSort() {
        if (sort == null) return Sort.unsorted();
        return (dir != null && dir == Sort.Direction.DESC) ?
                Sort.by(sort).descending(): Sort.by(sort).ascending();
    }

    private Pageable getPageRequest() {
        return PageRequest.of(
                (page != null) ? page: 0,
                (limit != null && limit >= 0) ? limit: 10,
                getSort()
        );
    }
    public SearchSpecPack<T> getSearchSpec() {
        return new SearchSpecPack<>(
                Specification.where(null),
                getPageRequest());
    }

}
