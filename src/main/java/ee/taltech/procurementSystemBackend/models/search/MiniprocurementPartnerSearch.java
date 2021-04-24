package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;

public class MiniprocurementPartnerSearch extends SearchObject<MiniprocurementPartner> {

    private Integer miniprocurementPartnerLinkId;
    private Integer miniprocurementPartnerProcurementId;
    private Integer miniprocurementPartnerPartnerId;
    private String miniprocurementPartnerTimeAdded;
    private String miniprocurementPartnerLinkFirstAccessed;
    private Integer miniprocurementPartnerId;

    public MiniprocurementPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer miniprocurementPartnerId, Integer miniprocurementPartnerLinkId, Integer miniprocurementPartnerProcurementId, Integer miniprocurementPartnerPartnerId, String miniprocurementPartnerTimeAdded, String miniprocurementPartnerLinkFirstAccessed) {
        super(limit, page, sort, dir);
        this.miniprocurementPartnerId = miniprocurementPartnerId;
        this.miniprocurementPartnerLinkId = miniprocurementPartnerLinkId;
        this.miniprocurementPartnerProcurementId = miniprocurementPartnerProcurementId;
        this.miniprocurementPartnerPartnerId = miniprocurementPartnerPartnerId;
        this.miniprocurementPartnerTimeAdded = miniprocurementPartnerTimeAdded;
        this.miniprocurementPartnerLinkFirstAccessed = miniprocurementPartnerLinkFirstAccessed;
    }


    @Override
    public SearchSpecPack<MiniprocurementPartner> getSearchSpec() {

        SearchSpecPack<MiniprocurementPartner> searchSpec = super.getSearchSpec();
        if (miniprocurementPartnerLinkId != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerLinkId", this.miniprocurementPartnerLinkId));
        }
        if (miniprocurementPartnerProcurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerProcurementId", this.miniprocurementPartnerProcurementId));
        }
        if (miniprocurementPartnerPartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerPartnerId", this.miniprocurementPartnerPartnerId));
        }
        if (miniprocurementPartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerId", this.miniprocurementPartnerId));
        }
        return searchSpec;
    }
}