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
import java.time.LocalDateTime;

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
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}