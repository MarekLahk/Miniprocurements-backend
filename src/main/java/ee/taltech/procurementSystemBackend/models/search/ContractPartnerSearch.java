package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ContractPartnerSearch extends SearchObject<ContractPartner> {

    private Integer contractPartnerContractId; //contractPartnerLinkId
    private Integer contractPartnerPartnerId;
    private Integer contractPartnerId;

    public ContractPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer contractPartnerId, Integer contractPartnerContractId, Integer contractPartnerPartnerId) {
        super(limit, page, sort, dir);
        this.contractPartnerId = contractPartnerId;
        this.contractPartnerContractId = contractPartnerContractId;
        this.contractPartnerPartnerId = contractPartnerPartnerId;
    }


    @Override
    public SearchSpecPack<ContractPartner> getSearchSpec() {

        SearchSpecPack<ContractPartner> searchSpec = super.getSearchSpec();
        if (contractPartnerContractId != null) {
            searchSpec.addSpec(Specifications.specEquals("contractPartnerContractId", this.contractPartnerContractId));
        }
        if (contractPartnerPartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("contractPartnerPartnerId", this.contractPartnerPartnerId));
        }
        if (contractPartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("contractPartnerId", this.contractPartnerId));
        }
        return searchSpec;
    }
}