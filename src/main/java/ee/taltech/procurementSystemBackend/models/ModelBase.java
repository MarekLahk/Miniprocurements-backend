package ee.taltech.procurementSystemBackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class ModelBase {

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    @Generated(GenerationTime.INSERT)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, updatable = false, insertable = false)
    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime updatedAt;
}
