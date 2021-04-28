package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Question")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends ModelBase {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Miniprocurement miniprocurement;
}
