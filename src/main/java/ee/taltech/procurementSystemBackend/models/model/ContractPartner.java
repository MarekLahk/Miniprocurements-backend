package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
@Getter
@Setter
public class ContractPartner extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_partner_id")
    private Integer contractPartnerId;
    @Basic
    @Column(name = "contract_id", nullable = false, length = -1)
    private Integer contractPartnerContractId;
    @Basic
    @Column(name = "partner_id", nullable = false, length = -1)
    private Integer contractPartnerPartnerId;
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