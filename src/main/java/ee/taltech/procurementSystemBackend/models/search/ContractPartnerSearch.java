package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ContractPartnerSearch extends SearchObject<ContractPartner> {

    private Integer ContractId; //contractPartnerLinkId
    private Integer PartnerId;
    private Integer id;

    public ContractPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer id, Integer ContractId, Integer PartnerId) {
        super(limit, page, sort, dir);
        this.id = id;
        this.ContractId = ContractId;
        this.PartnerId = PartnerId;
    }


    @Override
    public SearchSpecPack<ContractPartner> getSearchSpec() {

        SearchSpecPack<ContractPartner> searchSpec = super.getSearchSpec();
        if (ContractId != null) {
            searchSpec.addSpec(Specifications.specEquals("ContractId", this.ContractId));
        }
        if (PartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("PartnerId", this.PartnerId));
        }
        if (id != null) {
            searchSpec.addSpec(Specifications.specEquals("id", this.id));
        }
        return searchSpec;
    }
}