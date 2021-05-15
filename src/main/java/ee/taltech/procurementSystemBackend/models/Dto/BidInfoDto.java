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
@Schema(name = "BidInfoDto", description = "DTO for BidInfo")
public class BidInfoDto extends DtoBase {
    @Schema(title="ID of bidinfo")
    private Integer id;

    @NotBlank(message = "Procurement name cannot be blank.")
    @Schema(title="Name of bidinfo")
    private String name;

    @Positive(message = "Amount cannot be zero or negative.")
    @Schema(title="Amount of bidinfo")
    private Integer amount;

    @Schema(title="Description of bidinfo")
    private String description;

    @Schema(title="Requirements of bidinfo")
    private String requirements;

    @Schema(title="contractId of bidinfo")
    private Integer contractId;

    @Schema(title="contractSubId of bidinfo")
    private Integer contractSubId;

    @Schema(title="hasContract of bidinfo")
    private Boolean hasContract;

    @Schema(title="createdById of bidinfo")
    private Integer createdById;

    @NotNull(message = "Deadline cannot be null")
    @Schema(title="deadline of bidinfo")
    private Timestamp deadline;

    @Schema(title="status of bidinfo")
    private Short status;

    @Schema(title="finishedAt of bidinfo")
    private Timestamp finishedAt;

    @Schema(title="completionDeadline of bidinfo")
    private Timestamp completionDeadline;

    @Schema(title="completionDeadlineDays of bidinfo")
    private Timestamp completionDeadlineDays;

    @Schema(title="announcements of bidinfo")
    private List<AnnouncementDto> announcements;

    @Schema(title="questions of bidinfo")
    private List<QuestionInfoDto> questions;

    @Schema(title="documents of bidinfo")
    private List<DocumentDto> documents;

}
