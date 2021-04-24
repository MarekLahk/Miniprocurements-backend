package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class MiniprocurementPartnerDto extends DtoBase {
    private Integer miniprocurementPartnerId;

    private Integer miniprocurementPartnerLinkId;

    @NotNull(message = "miniprocurementPartnerProcurementId cannot be null")
    private Integer miniprocurementPartnerProcurementId;

    @NotNull(message = "miniprocurementPartnerPartnerId cannot be null")
    private Integer miniprocurementPartnerPartnerId;

//    private String miniprocurementPartnerTimeAdded;

    private String miniprocurementPartnerLinkFirstAccessed;
}