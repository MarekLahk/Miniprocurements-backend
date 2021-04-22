package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Question;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class QuestionSearch extends SearchObject<Question> {

    private String askerId;
    private Integer procurementId;

    public QuestionSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String askerId, Integer procurementId) {
        super(limit, page, sort, dir);
        this.askerId = askerId;
        this.procurementId = procurementId;
    }

    @Override
    public SearchSpecPack<Question> getSearchSpec() {

        SearchSpecPack<Question> searchSpec = super.getSearchSpec();
        if (askerId != null) {
            searchSpec.addSpec(Specifications.specEquals("askerId", this.askerId));
        }
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }

        return searchSpec;
    }
}
