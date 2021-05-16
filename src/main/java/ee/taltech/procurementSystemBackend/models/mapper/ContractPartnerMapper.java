package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ContractPartnerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractPartnerMapper extends MapperInterface<ContractPartner, ContractPartnerDto> {

    ContractPartnerMapper INSTANCE = Mappers.getMapper(ContractPartnerMapper.class);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    ContractPartnerDto toDto(ContractPartner model);

    @Override
    ContractPartner toModel(ContractPartnerDto dto);
}