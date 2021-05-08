package ee.taltech.procurementSystemBackend.models.search;

import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.model.Document;
import ee.taltech.procurementSystemBackend.repository.person.Specifications;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class DocumentSearch extends SearchObject<Document> {

    private final Integer id;
    private final UUID uuid;
    private final Integer procurementId;
    private final Integer bidId;
    private final Integer announcementId;
    private final Integer replyId;
    private final Integer personId;

    public DocumentSearch(Integer limit, Integer page, String sort, Sort.Direction dir, Integer id, UUID uuid, Integer procurementId, Integer bidId, Integer announcementId, Integer replyId, Integer personId) {
        super(limit, page, sort, dir);
        this.id = id;
        this.uuid = uuid;
        this.procurementId = procurementId;
        this.bidId = bidId;
        this.announcementId = announcementId;
        this.replyId = replyId;
        this.personId = personId;
    }

    @Override
    public SearchSpecPack<Document> getSearchSpec() {
        SearchSpecPack<Document> searchSpec = super.getSearchSpec();
        if (id != null) {
            searchSpec.addSpec(Specifications.specEquals("documentId", this.id));
        }
        if (uuid != null) {
            searchSpec.addSpec(Specifications.specEquals("linkId", this.uuid));
        }
        if (procurementId != null) {
            searchSpec.addSpec(Specifications.specEquals("procurementId", this.procurementId));
        }
        if (bidId != null) {
            searchSpec.addSpec(Specifications.specEquals("bidId", this.bidId));
        }
        if (announcementId != null) {
            searchSpec.addSpec(Specifications.specEquals("announcementId", this.announcementId));
        }
        if (replyId != null) {
            searchSpec.addSpec(Specifications.specEquals("replyId", this.replyId));
        }
        if (personId != null) {
            searchSpec.addSpec(Specifications.specEquals("personId", this.personId));
        }
        return searchSpec;
    }
}
