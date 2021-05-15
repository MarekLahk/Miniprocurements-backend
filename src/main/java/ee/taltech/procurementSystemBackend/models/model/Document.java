package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@JGlobalMap
public class Document extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", nullable = false)
    private Integer id;
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(name = "document_uuid", nullable = false)
    private UUID linkId;
    @Basic
    @Column(name = "document_name", nullable = false)
    private String name;
    @Basic
    @Column(name = "procurement_id")
    private Integer procurementId;
    @Basic
    @Column(name = "bid_id")
    private Integer bidId;
    @Basic
    @Column(name = "announcement_id")
    private Integer announcementId;
    @Basic
    @Column(name = "reply_id")
    private Integer replyId;
    @Basic
    @Column(name = "person_id", nullable = false)
    private Integer personId;
    @Basic
    @Column(name = "document_path", nullable = false)
    private String path;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procurement_id", insertable = false, updatable = false)
    private Procurement procurement;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id", insertable = false, updatable = false)
    private Bid bid;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id", insertable = false, updatable = false)
    private Announcement announcement;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id", insertable = false, updatable = false)
    private Reply reply;

}
