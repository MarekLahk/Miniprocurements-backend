package ee.taltech.procurementSystemBackend.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiddingResponse {

    private List<BidDto> bid;
    private MiniProcurementDto procurement;
    private List<QuestionAndRepliesResponse> questionsAndRelies;
}
