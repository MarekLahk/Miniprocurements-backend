package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MiniprocurementMapper extends MapperInterface<Miniprocurement, MiniProcurementDto> {

    MiniprocurementMapper INSTANCE = Mappers.getMapper(MiniprocurementMapper.class);

    @Override
    MiniProcurementDto toDto(Miniprocurement model);

    @Override
    Miniprocurement toModel(MiniProcurementDto dto);
}
