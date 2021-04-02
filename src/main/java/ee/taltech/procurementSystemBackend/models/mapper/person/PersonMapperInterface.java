package ee.taltech.procurementSystemBackend.models.mapper.person;

import ee.taltech.procurementSystemBackend.models.Dto.Person.PersonDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapperInterface<ModelT extends Person, DtoT extends PersonDto> extends MapperInterface<ModelT, DtoT> {

    PersonMapperInterface INSTANCE = null;

}
