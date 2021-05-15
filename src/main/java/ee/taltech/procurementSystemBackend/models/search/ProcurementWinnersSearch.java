package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinners;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ProcurementWinnersSearch extends SearchObject<ProcurementWinners> {

    private Integer procurementId;
    private Integer winnerId;

    public ProcurementWinnersSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementId, Integer winnerId) {
        super(limit, page, sort, dir);
        this.procurementId = procurementId;
        this.winnerId = winnerId;
    }

    @Override
    public SearchSpecPack<ProcurementWinners> getSearchSpec() {
        SearchSpecPack<ProcurementWinners> searchSpec = super.getSearchSpec();
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }
        if (winnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("winnerId", this.winnerId));
        }

        return searchSpec;
    }
}

