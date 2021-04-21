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
    @NotBlank(message = "MiniprocurementPartner cannot be blank")
    private String miniprocurementPartnerName;
    @NotBlank(message = "Name cannot be null.")
    private String miniprocurementPartnerEmail;
}