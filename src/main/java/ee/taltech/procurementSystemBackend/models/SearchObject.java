package ee.taltech.procurementSystemBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ee.taltech.procurementSystemBackend.models.search.SearchSpecPack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class SearchObject<T> {

    private Integer limit;
    private Integer page;
    private String sort;
    private Sort.Direction dir;

//    public SearchObject(Integer limit, Integer page, String sort, Sort.Direction dir) {
//        this.limit = limit;
//        this.page = page;
//        this.sort = sort;
//        this.dir = dir;
//    }

    //    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

    @JsonIgnore
    public SearchSpecPack<T> getSearchSpec() {
        return new SearchSpecPack<>(
                Specification.where(null),
                getPageRequest());
    }

}
