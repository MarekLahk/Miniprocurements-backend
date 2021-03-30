package ee.taltech.procurementSystemBackend.repository.person;

import ee.taltech.procurementSystemBackend.model.person.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PersonRepositoryInterface<T extends Person> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

    List<T> findAll(@Nullable Specification<T> spec);
    Optional<T> findPersonByeMail(String email);
    Optional<T> findByTimeOfRegisterBetween(LocalDateTime timeOfRegisterBefore, LocalDateTime timeOfRegisterAfter);
    Optional<T> findByTimeOfRegisterAfter(LocalDateTime timeOfRegister);
    Optional<T> findByTimeOfRegisterBefore(LocalDateTime timeOfRegister);


}
