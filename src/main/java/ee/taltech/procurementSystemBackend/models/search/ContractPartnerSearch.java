package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ContractPartnerSearch extends SearchObject<ContractPartner> {

    private Integer contractPartnerLinkId;
    private Integer contractPartnerProcurementId;
    private Integer contractPartnerPartnerId;
    private String contractPartnerTimeAdded;
    private String contractPartnerLinkFirstAccessed;
    private Integer contractPartnerId;

    public ContractPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer contractPartnerId, Integer contractPartnerLinkId, Integer contractPartnerProcurementId, Integer contractPartnerPartnerId, String contractPartnerTimeAdded, String contractPartnerLinkFirstAccessed) {
        super(limit, page, sort, dir);
        this.contractPartnerId = contractPartnerId;
        this.contractPartnerLinkId = contractPartnerLinkId;
        this.contractPartnerProcurementId = contractPartnerProcurementId;
        this.contractPartnerPartnerId = contractPartnerPartnerId;
        this.contractPartnerTimeAdded = contractPartnerTimeAdded;
        this.contractPartnerLinkFirstAccessed = contractPartnerLinkFirstAccessed;
    }


    @Override
    public SearchSpecPack<ContractPartner> getSearchSpec() {

        SearchSpecPack<ContractPartner> searchSpec = super.getSearchSpec();
        if (contractPartnerLinkId != null) {
            searchSpec.addSpec(Specifications.specEquals("contractPartnerLinkId", this.contractPartnerLinkId));
        }
        if (contractPartnerProcurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("contractPartnerProcurementId", this.contractPartnerProcurementId));
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