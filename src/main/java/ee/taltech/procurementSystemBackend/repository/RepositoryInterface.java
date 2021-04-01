package ee.taltech.procurementSystemBackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.util.List;

@NoRepositoryBean
public interface RepositoryInterface <T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

    List<T> findAll(@Nullable Specification<T> spec);
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
}
