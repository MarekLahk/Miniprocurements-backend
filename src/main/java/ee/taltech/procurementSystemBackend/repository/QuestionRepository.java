package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByProcurementId(Integer id);

    Question findByQuestionId(Integer id);
}
