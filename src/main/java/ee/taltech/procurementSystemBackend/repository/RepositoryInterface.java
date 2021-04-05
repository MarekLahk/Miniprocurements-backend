package ee.taltech.procurementSystemBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryInterface <T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

//    List<T> findAll(@Nullable Specification<T> spec);
//    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
}
