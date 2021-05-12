package ee.taltech.procurementSystemBackend.models;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
public abstract class ModelBase {

    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
