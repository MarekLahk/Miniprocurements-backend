package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartnerMapper extends PersonMapperInterface<Partner, PartnerDto> {

    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    @Override
    PartnerDto toDto(Partner model);

    @Override
    @Mapping(target = "procurementPartners", ignore = true)
    Partner toModel(PartnerDto dto);


}
