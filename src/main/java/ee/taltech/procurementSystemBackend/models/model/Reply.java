package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Reply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id", nullable = false)
    private Integer replyId;
    @Column(name = "replier_id", nullable = false)
    private Integer replierId;
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "reply")
    private String reply;
    @Column(name = "time_replied", nullable = false)
    private Timestamp timeReplied;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", insertable = false, updatable = false)
    private Question question;
}
