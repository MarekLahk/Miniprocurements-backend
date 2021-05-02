package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProcurementMapper extends MapperInterface<Procurement, ProcurementDto> {

    ProcurementMapper INSTANCE = Mappers.getMapper(ProcurementMapper.class);

    @Override
    ProcurementDto toDto(Procurement model);

    @Override
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "bids", ignore = true)
    @Mapping(target = "procurementPartners", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Procurement toModel(ProcurementDto dto);
}
