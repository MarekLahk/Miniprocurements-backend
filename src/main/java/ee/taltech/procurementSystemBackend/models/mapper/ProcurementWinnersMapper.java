package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnersDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinners;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcurementWinnersMapper extends MapperInterface<ProcurementWinners, ProcurementWinnersDto> {

    ProcurementWinnersMapper INSTANCE = Mappers.getMapper(ProcurementWinnersMapper.class);

    @Override
    ProcurementWinnersDto toDto(ProcurementWinners model);

    @Override
    ProcurementWinners toModel(ProcurementWinnersDto dto);
}

