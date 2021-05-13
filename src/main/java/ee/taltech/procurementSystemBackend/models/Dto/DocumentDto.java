package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDto extends DtoBase {

    @Schema(title="UUID for accessing a document")
    private UUID linkId;

    @Schema(title="Full name of the document. Includes extension")
    private String name;

}
