package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.model.person.Partner;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PartnerMapper.class, EmployeeMapper.class})
public interface PersonMapper extends PersonMapperInterface<Person, PersonDto> {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);
    PartnerMapper PARTNER_MAPPER = Mappers.getMapper(PartnerMapper.class);

    @Override
    default PersonDto toDto(@MappingTarget Person model) {
        if (model == null) return null;
        if (model instanceof Employee) {
            return EMPLOYEE_MAPPER.toDto((Employee) model);
        }
        if (model instanceof Partner) {
            return PARTNER_MAPPER.toDto((Partner) model);
        }
        return null;
    }

    @Override
    Person toModel(PersonDto dto);


}
