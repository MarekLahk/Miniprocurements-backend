package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcurementPartnerMapper extends MapperInterface<ProcurementPartner, ProcurementPartnerDto> {

    ProcurementPartnerMapper INSTANCE = Mappers.getMapper(ProcurementPartnerMapper.class);

    @Override
    ProcurementPartnerDto toDto(ProcurementPartner model);

    @Override
    @Mapping(target = "procurement", ignore = true)
    ProcurementPartner toModel(ProcurementPartnerDto dto);
}