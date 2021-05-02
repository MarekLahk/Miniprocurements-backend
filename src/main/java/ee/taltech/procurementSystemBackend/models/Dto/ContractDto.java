package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto extends DtoBase  {

    private Integer contractId;
    private Integer contractReferenceNumber;
    private Integer procurementTemplateId;
    private Integer bidTemplateId;
    private String contractName;
    private Timestamp timeAdded;
}

/*
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false)
    private Integer contractId;
    @Basic
    @Column(name = "contract_reference_number", nullable = false)
    private Integer contractReferenceNumber;
    @Basic
    @Column(name = "procurement_template_id", nullable = false)
    private Integer procurementTemplateId;
    @Basic
    @Column(name = "bid_template_id", nullable = false)
    private Integer bidTemplateId;
    @Basic
    @Column(name = "contract_name", nullable = false, length = 50)
    private String contractName;
    @Basic
    @Column(name = "date_added", nullable = false)
    private Timestamp timeAdded;
}
 */