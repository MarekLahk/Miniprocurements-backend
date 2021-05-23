package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProcurementSearch extends SearchObject<Procurement> {

    private final String name;
    private final Integer createdBy;
    private final Short status;

//    public ProcurementSearch(Integer limit, Integer page, String sort, Sort.Direction dir, String procurementName, Integer createdBy, Short status) {
//        super(limit, page, sort, dir);
//        this.procurementName = procurementName;
//        this.createdBy = createdBy;
//        this.status = status;
//    }

    @Override
    public SearchSpecPack<Procurement> getSearchSpec() {

        SearchSpecPack<Procurement> searchSpec = super.getSearchSpec();
        if (name != null) {
            searchSpec.addSpec(Specifications.specEquals("name", this.name));
        }
        if (createdBy != null) {
            searchSpec.addSpec(Specifications.specEquals("createdById", this.createdBy));
        }
        if (status != null) {
            searchSpec.addSpec(Specifications.specEquals("status", this.status));
        }

        return searchSpec;
    }
}
