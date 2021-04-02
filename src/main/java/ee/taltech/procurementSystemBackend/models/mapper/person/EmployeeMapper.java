package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.EmployeeDto;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper extends PersonMapperInterface<Employee, EmployeeDto> {

     EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

     @Override
     EmployeeDto toDto(Employee model);

     @Override
     Employee toModel(EmployeeDto dto);


}
