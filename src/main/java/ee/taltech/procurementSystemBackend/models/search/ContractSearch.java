package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Contract;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ContractSearch extends SearchObject<Contract> {

    private String contractName;
    private Integer addedBy;
    private Short status;

    public ContractSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String contractName, Integer addedBy, Short status) {
        super(limit, page, sort, dir);
        this.contractName = contractName;
        this.addedBy = addedBy;
        this.status = status;
    }

    @Override
    public SearchSpecPack<Contract> getSearchSpec() {

        SearchSpecPack<Contract> searchSpec = super.getSearchSpec();
        if (contractName != null) {
            searchSpec.addSpec(Specifications.specEquals("contractName", this.contractName));
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
