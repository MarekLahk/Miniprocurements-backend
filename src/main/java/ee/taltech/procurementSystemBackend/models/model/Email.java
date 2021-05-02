package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class Email extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", nullable = false)
    private Integer emailId;
    @Basic
    @Column(name = "procurement_id")
    private Integer procurementId;
    @Basic
    @Column(name = "reply_id")
    private String replyId;
    @Basic
    @Column(name = "announcement_id")
    private String announcementId;
    @Basic
    @Column(name = "recipient_id", nullable = false)
    private Integer recipientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Miniprocurement miniprocurement;

}
