package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcurementWinnerMapper extends MapperInterface<ProcurementWinner, ProcurementWinnerDto> {

    ProcurementWinnerMapper INSTANCE = Mappers.getMapper(ProcurementWinnerMapper.class);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    ProcurementWinnerDto toDto(ProcurementWinner model);

    @Override
    ProcurementWinner toModel(ProcurementWinnerDto dto);
}

