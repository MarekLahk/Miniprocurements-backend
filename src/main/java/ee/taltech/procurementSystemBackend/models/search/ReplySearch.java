package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.*;

//@EqualsAndHashCode(callSuper = true)
//@Getter
//@Setter
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ReplySearch extends SearchObject<Reply> {

    private Integer replierId;
    private Integer questionId;
    private Integer procurementId;

//    public ReplySearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer replierId, Integer questionId, Integer procurementId) {
//        super(limit, page, sort, dir);
//        this.replierId = replierId;
//        this.questionId = questionId;
//        this.procurementId = procurementId;
//    }

    @Override
    public SearchSpecPack<Reply> getSearchSpec() {

        SearchSpecPack<Reply> searchSpec = super.getSearchSpec();
        if (replierId != null) {
            searchSpec.addSpec(Specifications.specEquals("replierId", this.replierId));
        }
        if (questionId != null) {
            searchSpec.addSpec(Specifications.specEquals("questionId", this.questionId));
        }
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }

        return searchSpec;
    }
}
