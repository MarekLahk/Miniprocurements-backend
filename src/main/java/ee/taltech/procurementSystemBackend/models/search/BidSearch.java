package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.UUID;

public class BidSearch extends SearchObject<Bid> {

    private Integer procurementID;
    private UUID bidderLinkId;
    private Long bidValue;
    private Integer bidStatus;
    private Timestamp timeOfRegister;

    public BidSearch(Integer limit, Integer page, String sort, Sort.Direction dir,
                     Integer procurementID,
                     UUID bidderLinkId,
                     Long bidValue,
                     Integer bidStatus,
                     Timestamp timeOfRegister) {
        super(limit, page, sort, dir);
        this.procurementID = procurementID;
        this.bidderLinkId = bidderLinkId;
        this.bidValue = bidValue;
        this.bidStatus = bidStatus;
        this.timeOfRegister = timeOfRegister;
    }

    @Override
    public SearchSpecPack<Bid> getSearchSpec() {
        SearchSpecPack<Bid> searchSpec = super.getSearchSpec();
        if (procurementID != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementID));
        }
        if (bidderLinkId != null) {
            searchSpec.addSpec(Specifications.specEquals("bidderLinkId", this.bidderLinkId));
        }
        if (bidValue != null) {
            searchSpec.addSpec(Specifications.specEquals("bidValue", this.bidValue));
        }
        if (bidStatus != null) {
            searchSpec.addSpec(Specifications.specEquals("bidStatus", this.bidStatus));
        }
        if (timeOfRegister != null) {
            searchSpec.addSpec(Specifications.specEquals("timeOfRegister", this.timeOfRegister));
        }
        return searchSpec;
    }
}
