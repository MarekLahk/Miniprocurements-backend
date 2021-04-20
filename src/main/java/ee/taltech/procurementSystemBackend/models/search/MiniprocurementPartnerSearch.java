package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class MiniprocurementPartnerSearch extends SearchObject<MiniprocurementPartner> {

    private Integer procurementID;
    private Integer employeeID;

    public MiniprocurementPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementID, Integer employeeID) {
        super(limit, page, sort, dir);
        this.procurementID = procurementID;
        this.employeeID = employeeID;
    }


    @Override
    public SearchSpecPack<MiniprocurementPartner> getSearchSpec() {

        SearchSpecPack<MiniprocurementPartner> searchSpec = super.getSearchSpec();
        if (procurementID != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementID));
        }
        if (employeeID != null) {
            searchSpec.addSpec(Specifications.specEquals("employeeId", this.employeeID));
        }

        return searchSpec;
    }
}