package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

public class AnnouncementSearch extends SearchObject<Announcement> {

    private final Integer procurementID;
    private final Integer employeeID;

    public AnnouncementSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementID, Integer employeeID) {
        super(limit, page, sort, dir);
        this.procurementID = procurementID;
        this.employeeID = employeeID;
    }


    @Override
    public SearchSpecPack<Announcement> getSearchSpec() {

        SearchSpecPack<Announcement> searchSpec = super.getSearchSpec();
        if (procurementID != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementID));
        }
        if (employeeID != null) {
            searchSpec.addSpec(Specifications.specEquals("employeeId", this.employeeID));
        }

        return searchSpec;
    }
}
