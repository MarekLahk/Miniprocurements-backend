package ee.taltech.procurementSystemBackend.repository;

import ee.taltech.procurementSystemBackend.models.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    List<Reply> findAllByProcurementId(Integer id);

    List<Reply> findAllByQuestionId(Integer id);

}
