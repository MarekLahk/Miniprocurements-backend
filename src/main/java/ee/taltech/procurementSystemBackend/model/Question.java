package ee.taltech.procurementSystemBackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Question")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    @Column(name = "asker_id", nullable = false)
    private String askerId;
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "question", nullable = false)
    private String question;
    @Column(name = "time_asked", nullable = false)
    private Timestamp timeAsked;
}
