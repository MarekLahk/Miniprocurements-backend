package ee.taltech.procurementSystemBackend.models.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "Procurement_winners")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class ProcurementWinner extends ModelBase {

    @Id
    @Column(name = "procurement_id", nullable = false)
    private Integer procurementId;
    @Column(name = "winner_id", nullable = false)
    private Integer winnerId;
    @Column(name = "judge_id", nullable = false)
    private Integer judgeId;
    @Column(name = "reason")
    private String reason;
}

