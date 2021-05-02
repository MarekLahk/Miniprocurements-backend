package ee.taltech.procurementSystemBackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ModelBase {

    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
