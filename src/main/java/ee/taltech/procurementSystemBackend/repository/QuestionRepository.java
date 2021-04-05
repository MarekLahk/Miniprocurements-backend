package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Question;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends RepositoryInterface<Question> {

    Optional<Question> findByQuestionIdAndProcurementId(Integer questionId, Integer procurementId);
}
