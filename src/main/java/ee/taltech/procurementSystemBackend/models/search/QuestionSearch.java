package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class QuestionSearch extends SearchObject<Question> {

    private UUID bidderLinkId;
    private Integer procurementId;

//    public QuestionSearch(Integer limit, Integer page, String sort, Sort.Direction dir, UUID bidderLinkId, Integer procurementId) {
//        super(limit, page, sort, dir);
//        this.bidderLinkId = bidderLinkId;
//        this.procurementId = procurementId;
//    }

    @Override
    public SearchSpecPack<Question> getSearchSpec() {

        SearchSpecPack<Question> searchSpec = super.getSearchSpec();
        if (bidderLinkId != null) {
            searchSpec.addSpec(Specifications.specEquals("linkId", this.bidderLinkId));
        }
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }

        return searchSpec;
    }
}
