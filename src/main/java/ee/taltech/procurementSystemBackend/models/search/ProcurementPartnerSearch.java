package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class ProcurementPartnerSearch extends SearchObject<ProcurementPartner> {

    private final Integer linkId;
    private final Integer procurementId;
    private final Integer partnerId;
    private final Integer id;

    public ProcurementPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer id, Integer linkId, Integer procurementId, Integer partnerId) {
        super(limit, page, sort, dir);
        this.id = id;
        this.linkId = linkId;
        this.procurementId = procurementId;
        this.partnerId = partnerId;
    }


    @Override
    public SearchSpecPack<ProcurementPartner> getSearchSpec() {

        SearchSpecPack<ProcurementPartner> searchSpec = super.getSearchSpec();
        if (linkId != null) {
            searchSpec.addSpec(Specifications.specEquals("linkId", this.linkId));
        }
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }
        if (partnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("partnerId", this.partnerId));
        }
        if (id != null) {
            searchSpec.addSpec(Specifications.specEquals("id", this.id));
        }
        return searchSpec;
    }
}