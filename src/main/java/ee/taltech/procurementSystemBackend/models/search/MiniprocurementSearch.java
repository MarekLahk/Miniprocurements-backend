package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class MiniprocurementSearch extends SearchObject<Miniprocurement> {

    private String procurementName;
    private Integer addedBy;
    private Short status;

    public MiniprocurementSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String procurementName, Integer addedBy, Short status) {
        super(limit, page, sort, dir);
        this.procurementName = procurementName;
        this.addedBy = addedBy;
        this.status = status;
    }

    @Override
    public SearchSpecPack<Miniprocurement> getSearchSpec() {

        SearchSpecPack<Miniprocurement> searchSpec = super.getSearchSpec();
        if (procurementName != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementName", this.procurementName));
        }
        if (addedBy != null) {
            searchSpec.addSpec(Specifications.specEquals("addedBy", this.addedBy));
        }
        if (status != null) {
            searchSpec.addSpec(Specifications.specEquals("status", this.status));
        }

        return searchSpec;
    }
}
