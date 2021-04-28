package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Getter
@Setter
public class MiniprocurementPartner extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer miniprocurementPartnerId;
    @Basic
    @Column(name = "link_id")
    private UUID miniprocurementPartnerLinkId;
    @Basic
    @Column(name = "procurement_id", nullable = false, length = -1)
    private Integer miniprocurementPartnerProcurementId;
    @Basic
    @Column(name = "partner_id", nullable = false, length = -1)
    private Integer miniprocurementPartnerPartnerId;
    //@Basic
    //@Column(name = "time_added", nullable = false, length = -1)
    //private String miniprocurementPartnerTimeAdded;
    @Basic
    @Column(name = "link_first_accessed", nullable = true, length = -1)
    private String miniprocurementPartnerLinkFirstAccessed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procurement_id", insertable = false, updatable = false)
    private Miniprocurement miniprocurement;
}


/*
    CREATE TABLE MiniprocurementPartner(
        link_id BINARY(16) NOT NULL,
    procurement_id MEDIUMINT NOT NULL,
    partner_id MEDIUMINT NOT NULL,
    time_added DATETIME NOT NULL DEFAULT NOW(),
    link_first_accessed DATETIME DEFAULT NULL,
    CONSTRAINT pk_link_id PRIMARY KEY (link_id),
    CONSTRAINT fk_procurement_link_partner_id FOREIGN KEY (partner_id)
    REFERENCES Partner(partner_id),
    CONSTRAINT fk_procurement_link_procurement_id FOREIGN KEY (procurement_id)
    REFERENCES Miniprocurement(procurement_id)
);
*/