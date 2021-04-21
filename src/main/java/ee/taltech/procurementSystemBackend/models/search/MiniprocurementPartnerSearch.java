package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class MiniprocurementPartnerSearch extends SearchObject<MiniprocurementPartner> {

    private String miniprocurementPartnerEmail;
    private String miniprocurementPartnerName;

    public MiniprocurementPartnerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String miniprocurementPartnerEmail, String miniprocurementPartnerName) {
        super(limit, page, sort, dir);
        this.miniprocurementPartnerEmail = miniprocurementPartnerEmail;
        this.miniprocurementPartnerName = miniprocurementPartnerName;
    }


    @Override
    public SearchSpecPack<MiniprocurementPartner> getSearchSpec() {

        SearchSpecPack<MiniprocurementPartner> searchSpec = super.getSearchSpec();
        if (miniprocurementPartnerName != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerName", this.miniprocurementPartnerName));
        }
        if (miniprocurementPartnerEmail != null) {
            searchSpec.addSpec(Specifications.specEquals("miniprocurementPartnerEmail", this.miniprocurementPartnerEmail));
        }

        return searchSpec;
    }
}