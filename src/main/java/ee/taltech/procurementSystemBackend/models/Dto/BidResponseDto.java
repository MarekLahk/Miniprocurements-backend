package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "BidResponseDto", description = "DTO for BidResponse")
public class BidResponseDto extends DtoBase {
    @Schema(title="ID of bidresponse")
    private Integer id;

    @NotBlank(message = "Procurement name cannot be blank.")
    @Schema(title="Name of bidresponse")
    private String name;

    @Positive(message = "Amount cannot be zero or negative.")
    @Schema(title="Amount of bidresponse")
    private Integer amount;

    @Schema(title="Description of bidresponse")
    private String description;

    @Schema(title="Requirements of bidresponse")
    private String requirements;

    @Schema(title="contractId of bidresponse")
    private Integer contractId;

    @Schema(title="contractSubId of bidresponse")
    private Integer contractSubId;

    @Schema(title="hasContract of bidresponse")
    private Boolean hasContract;

    @Schema(title="createdById of bidresponse")
    private Integer createdById;

    @NotNull(message = "Deadline cannot be null")
    @Schema(title="deadline of bidresponse")
    private Timestamp deadline;

    @Schema(title="status of bidresponse")
    private Short status;

    @Schema(title="finishedAt of bidresponse")
    private Timestamp finishedAt;

    @Schema(title="completionDeadline of bidresponse")
    private Timestamp completionDeadline;

    @Schema(title="completionDeadlineDays of bidresponse")
    private Timestamp completionDeadlineDays;

    @Schema(title="announcements of bidresponse")
    private List<AnnouncementDto> announcements;

    @Schema(title="questions of bidresponse")
    private List<QuestionInfoDto> questions;
}
