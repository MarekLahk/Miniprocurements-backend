package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ReplyDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Reply;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyMapper extends MapperInterface<Reply, ReplyDto> {

    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    ReplyDto toDto(Reply model);

    @Override
    @Mapping(target = "question", ignore = true)
    Reply toModel(ReplyDto dto);
}
