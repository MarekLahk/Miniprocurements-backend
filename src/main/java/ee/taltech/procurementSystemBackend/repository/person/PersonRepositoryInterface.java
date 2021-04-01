package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Partner;
import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PersonRepositoryInterface<T extends Person> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

    List<T> findAll(@Nullable Specification<T> spec);
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

}
