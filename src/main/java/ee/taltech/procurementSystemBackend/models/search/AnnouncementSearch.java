package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AnnouncementSearch extends SearchObject<Announcement> {

    private Integer procurementID;
    private Integer employeeID;

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
