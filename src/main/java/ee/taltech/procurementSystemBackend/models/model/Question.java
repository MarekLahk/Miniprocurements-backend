package ee.taltech.procurementSystemBackend.models.model;

import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    private Integer id;
    @Column(name = "bidder_link_id", nullable = false)
    private UUID linkId;
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "question_text", nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Procurement procurement;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", targetEntity = Reply.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Reply> replies;
}
