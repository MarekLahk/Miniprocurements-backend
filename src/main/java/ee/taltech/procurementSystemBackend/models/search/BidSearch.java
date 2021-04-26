package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class BidSearch extends SearchObject<Bid> {

    private Integer procurementID;

    public BidSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementID) {
        super(limit, page, sort, dir);
        this.procurementID = procurementID;
    }

    @Override
    public SearchSpecPack<Bid> getSearchSpec() {
        SearchSpecPack<Bid> searchSpec = super.getSearchSpec();
        if (procurementID != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementID));
        }
        return searchSpec;
    }
}
