package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.QuestionDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper extends MapperInterface<Question, QuestionDto> {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Override
    QuestionDto toDto(Question model);

    @Override
    Question toModel(QuestionDto dto);
}
