package ee.taltech.procurementSystemBackend.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DtoBase {

    private LocalDateTime createdAt;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;
}
