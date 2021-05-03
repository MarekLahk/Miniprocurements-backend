package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PartnerDto;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartnerMapper extends PersonMapperInterface<Partner, PartnerDto> {

    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    PartnerDto toDto(Partner model);

    @Override
    Partner toModel(PartnerDto dto);


}
