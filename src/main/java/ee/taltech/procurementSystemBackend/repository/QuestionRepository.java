package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends RepositoryInterface<Question> {

    Optional<Question> findByIdAndProcurementId(Integer questionId, Integer procurementId);

    List<Question> findAllByProcurementId(Integer id);
}
