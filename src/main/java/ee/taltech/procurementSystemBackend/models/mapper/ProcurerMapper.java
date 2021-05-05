package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProcurerMapper extends MapperInterface<Procurer, ProcurerDto> {

    ProcurerMapper INSTANCE = Mappers.getMapper(ProcurerMapper.class);

    @Override
    ProcurerDto toDto(Procurer model);

    @Override
    Procurer toModel(ProcurerDto dto);

    @Override
    List<ProcurerDto> toDtoList(List<Procurer> dtoList);
}
