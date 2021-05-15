package ee.taltech.procurementSystemBackend.models.Dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {

    private Integer procurementId;

    private Integer announcementId;

    private Integer replyId;

    private MultipartFile file;

}
