package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ContractPartnerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractPartnerMapper extends MapperInterface<ContractPartner, ContractPartnerDto> {

    ContractPartnerMapper INSTANCE = Mappers.getMapper(ContractPartnerMapper.class);

    @Override
    ContractPartnerDto toDto(ContractPartner model);

    @Override
    ContractPartner toModel(ContractPartnerDto dto);
}