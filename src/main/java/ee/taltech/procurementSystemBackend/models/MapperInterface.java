package ee.taltech.procurementSystemBackend.models;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel="spring" )

public interface MapperInterface<ModelT extends ModelBase, DtoT extends DtoBase> {

    MapperInterface INSTANCE = null;


    default DtoT toDto(ModelT model) {
        return null;
    }

    default ModelT toModel(DtoT dto) {
        return null;
    }

    @IterableMapping
    default List<DtoT> toDtoList(List<ModelT> dtoList) {
        return null;
    }



}
