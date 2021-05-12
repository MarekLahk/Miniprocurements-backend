package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
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
public class BidResponseDto extends DtoBase {

    private Integer id;
    @NotBlank(message = "Procurement name cannot be blank.")
    private String name;
    @Positive(message = "Amount cannot be zero or negative.")
    private Integer amount;
    private String description;
    private String requirements;
    private Integer contractId;
    private Integer contractSubId;
    private Boolean hasContract;
    private Integer createdById;
    @NotNull(message = "Deadline cannot be null")
    private Timestamp deadline;
    private Short status;
    private Timestamp finishedAt;
    private Timestamp completionDeadline;
    private Timestamp completionDeadlineDays;

    private List<AnnouncementDto> announcements;
    private List<QuestionInfoDto> questions;

}
