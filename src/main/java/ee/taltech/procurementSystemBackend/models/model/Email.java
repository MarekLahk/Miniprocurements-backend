package ee.taltech.procurementSystemBackend.models.model;

import javax.persistence.*;
import java.sql.Timestamp;

public class Email {

    //Todo Finish class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", nullable = false)
    private Integer emailId;
    @Basic
    @Column(name = "is_sent", nullable = false)
    private Integer isSent;
    @Basic
    @Column(name = "procurement_id")
    private Integer procurementId;
    @Basic
    @Column(name = "reply_id")
    private String replyId;
    @Basic
    @Column(name = "date_added", nullable = false)
    private Timestamp dateAdded;

}
