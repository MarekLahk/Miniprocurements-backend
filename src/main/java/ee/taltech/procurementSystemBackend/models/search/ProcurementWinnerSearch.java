package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProcurementWinnerSearch extends SearchObject<ProcurementWinner> {

    private Integer procurementId;
    private Integer winnerId;

//    public ProcurementWinnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementId, Integer winnerId) {
//        super(limit, page, sort, dir);
//        this.procurementId = procurementId;
//        this.winnerId = winnerId;
//    }

    @Override
    public SearchSpecPack<ProcurementWinner> getSearchSpec() {
        SearchSpecPack<ProcurementWinner> searchSpec = super.getSearchSpec();
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }
        if (winnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("winnerId", this.winnerId));
        }

        return searchSpec;
    }
}

