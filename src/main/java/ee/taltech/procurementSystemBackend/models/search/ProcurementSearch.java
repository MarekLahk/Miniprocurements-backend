package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ProcurementSearch extends SearchObject<Procurement> {

    private final String procurementName;
    private final Integer addedBy;
    private final Short status;

    public ProcurementSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String procurementName, Integer addedBy, Short status) {
        super(limit, page, sort, dir);
        this.procurementName = procurementName;
        this.addedBy = addedBy;
        this.status = status;
    }

    @Override
    public SearchSpecPack<Procurement> getSearchSpec() {

        SearchSpecPack<Procurement> searchSpec = super.getSearchSpec();
        if (procurementName != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementName", this.procurementName));
        }
        if (addedBy != null) {
            searchSpec.addSpec(Specifications.specEquals("createdBy", this.addedBy));
        }
        if (status != null) {
            searchSpec.addSpec(Specifications.specEquals("status", this.status));
        }

        return searchSpec;
    }
}
