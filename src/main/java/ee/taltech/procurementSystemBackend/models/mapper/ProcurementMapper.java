package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.*;
import ee.taltech.procurementSystemBackend.models.Dto.BidInfoDto;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.Dto.QuestionInfoDto;
import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProcurementMapper extends MapperInterface<Procurement, ProcurementDto> {

    ProcurementMapper INSTANCE = Mappers.getMapper(ProcurementMapper.class);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    ProcurementDto toDto(Procurement model);



    @Override
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "bids", ignore = true)
    @Mapping(target = "procurementPartners", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Procurement toModel(ProcurementDto dto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    BidInfoDto toInfoDto(Procurement model);

    @Mapping(source = "questions", target = "questions")
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<QuestionInfoDto> toQuestionInfoDtoList(List<Question> questions);
    QuestionInfoDto toQuestionInfoDto(Question question);
    @Mapping(source = "replies", target = "replies")
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<ReplyDto> toReplyDtoList(List<Reply> questions);
    @Mapping(source = "documents", target = "documents")
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<DocumentDto> toDocumentDto(List<Document> documents);
    @Mapping(source = "announcements", target = "announcements")
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<AnnouncementInfoDto> toAnnouncementDto(List<Announcement> announcements);
}
