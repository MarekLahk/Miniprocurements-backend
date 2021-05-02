package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto extends DtoBase {

    private Integer id;
    @NotNull(message = "Replier id cannot be null.")
    private Integer replierId;
    @NotNull(message = "Question id cannot be null.")
    private Integer questionId;
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;
    private String reply;
}
