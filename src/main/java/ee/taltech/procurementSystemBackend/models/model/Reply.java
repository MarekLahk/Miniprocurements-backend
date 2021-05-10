package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    private Integer id;
    @Column(name = "replier_id", nullable = false)
    private Integer replierId;
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "reply")
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", insertable = false, updatable = false)
    private Question question;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reply")
    private Set<Document> documents;
}
