package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BidSearch extends SearchObject<Bid> {

    private Integer procurementID;
    private UUID linkId;
    private Long value;
    private Integer status;
    private Integer procurementPartnerId;

//    public BidSearch(Integer limit, Integer page, String sort, Sort.Direction dir,
//                     Integer procurementID,
//                     UUID linkId,
//                     Long value,
//                     Integer status,
//                     Integer procurementPartnerId) {
//        super(limit, page, sort, dir);
//        this.procurementID = procurementID;
//        this.linkId = linkId;
//        this.value = value;
//        this.status = status;
//        this.procurementPartnerId = procurementPartnerId;
//    }

    @Override
    public SearchSpecPack<Bid> getSearchSpec() {
        SearchSpecPack<Bid> searchSpec = super.getSearchSpec();
        if (procurementID != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementID));
        }
        if (linkId != null) {
            searchSpec.addSpec(Specifications.specEquals("linkId", this.linkId));
        }
        if (value != null) {
            searchSpec.addSpec(Specifications.specEquals("value", this.value));
        }
        if (status != null) {
            searchSpec.addSpec(Specifications.specEquals("status", this.status));
        }
        if (procurementPartnerId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementPartnerId", this.procurementPartnerId));
        }
        return searchSpec;
    }
}
