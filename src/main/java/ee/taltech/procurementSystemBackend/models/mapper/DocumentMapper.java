package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.DocumentDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper extends MapperInterface<Document, DocumentDto> {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Override
    DocumentDto toDto(Document model);

    @Override
    Document toModel(DocumentDto dto);
}
