package ee.taltech.procurementSystemBackend.models.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSpecPack<T> {

    Specification<T> specification;
    Pageable pageable;

    public void addSpec(Specification<T> spec) {
        this.specification = this.specification.and(spec);
    }

}
