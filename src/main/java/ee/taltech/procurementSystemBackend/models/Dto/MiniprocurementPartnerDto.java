package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class MiniprocurementPartnerDto extends DtoBase {
    private Integer miniprocurementPartnerId;

    private UUID miniprocurementPartnerLinkId;

    @NotNull(message = "miniprocurementPartnerProcurementId cannot be null")
    private Integer miniprocurementPartnerProcurementId;

    @NotNull(message = "miniprocurementPartnerPartnerId cannot be null")
    private Integer miniprocurementPartnerPartnerId;

//    private String miniprocurementPartnerTimeAdded;

    private String miniprocurementPartnerLinkFirstAccessed;
}