package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper extends PersonMapperInterface<Employee, EmployeeDto> {

     EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

     @Override
//     @Mapping(target = "regNr", ignore = true)
//     @Mapping(target = "partnerInfo", ignore = true)
     @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
     EmployeeDto toDto(Employee model);

     @Override
     @Mapping(target = "procurements", ignore = true)
     Employee toModel(EmployeeDto dto);


}
