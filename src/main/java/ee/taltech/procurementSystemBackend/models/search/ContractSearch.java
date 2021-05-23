package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Contract;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ContractSearch extends SearchObject<Contract> {

    private String contractName;
    private Short status;
    private Long referenceNumber;
//
//    public ContractSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String contractName, Short status) {
//        super(limit, page, sort, dir);
//        this.contractName = contractName;
//        this.status = status;
//    }

    @Override
    public SearchSpecPack<Contract> getSearchSpec() {

        SearchSpecPack<Contract> searchSpec = super.getSearchSpec();
        if (contractName != null) {
            searchSpec.addSpec(Specifications.specEquals("contractName", this.contractName));
        }
        if (status != null) {
            searchSpec.addSpec(Specifications.specEquals("status", this.status));
        }

        return searchSpec;
    }
}
