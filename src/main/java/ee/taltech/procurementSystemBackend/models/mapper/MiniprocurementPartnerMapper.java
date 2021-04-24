package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.MiniprocurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MiniprocurementPartnerMapper extends MapperInterface<MiniprocurementPartner, MiniprocurementPartnerDto> {

    MiniprocurementPartnerMapper INSTANCE = Mappers.getMapper(MiniprocurementPartnerMapper.class);

    @Override
    MiniprocurementPartnerDto toDto(MiniprocurementPartner model);

    @Override
    MiniprocurementPartner toModel(MiniprocurementPartnerDto dto);
}