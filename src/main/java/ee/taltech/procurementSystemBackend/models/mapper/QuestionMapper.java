package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Question;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper extends MapperInterface<Question, QuestionDto> {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Override
    QuestionDto toDto(Question model);

    @Override
    @Mapping(target = "procurement", ignore = true)
    @Mapping(target = "replies", ignore = true)
    Question toModel(QuestionDto dto);

}
