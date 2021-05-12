package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.ContractDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper extends MapperInterface<Contract, ContractDto> {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Override
    ContractDto toDto(Contract model);

    @Override
    Contract toModel(ContractDto dto);
}
