package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;


public class ProcurerSearch extends SearchObject<Procurer> {

    private Integer procurementId;
    private Integer employeeId;

    public ProcurerSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer procurementId, Integer employeeId) {
        super(limit, page, sort, dir);
        this.procurementId = procurementId;
        this.employeeId = employeeId;
    }

    @Override
    public SearchSpecPack<Procurer> getSearchSpec() {

        SearchSpecPack<Procurer> searchSpec = super.getSearchSpec();
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }
        if (employeeId != null) {
            searchSpec.addSpec(Specifications.specEquals("employeeId", this.employeeId));
        }

        return searchSpec;
    }
}
