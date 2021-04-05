package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.ModelBase;
import ee.taltech.procurementSystemBackend.models.SearchObject;
import ee.taltech.procurementSystemBackend.models.search.SearchSpecPack;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ServiceBase<ModelT extends ModelBase, DtoT extends DtoBase> {

    private final RepositoryInterface<ModelT> repository;
    private final MapperInterface<ModelT, DtoT> mapper;


    public ServiceBase(RepositoryInterface<ModelT> repository, MapperInterface<ModelT, DtoT> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<DtoT> toDtoOptional(ModelT model) {
        if (model != null) return Optional.of(mapper.toDto(model));
        return Optional.empty();
    }

    public List<DtoT> toDtoList(List<ModelT> modelList) {
        return modelList.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<ModelT> toModelOptional(DtoT dto) {
        if (dto != null) return Optional.of(mapper.toModel(dto));
        return Optional.empty();
    }
    public List<ModelT> toModelList(List<DtoT> dtoList) {
        return dtoList.stream().map(mapper::toModel).collect(Collectors.toList());
    }

    public List<DtoT> getByParams(SearchObject<ModelT> searchObject) {
        SearchSpecPack<ModelT> searchSpec = searchObject.getSearchSpec();
        List<ModelT> result = repository.findAll(searchSpec.getSpecification(), searchSpec.getPageable()).getContent();
        return toDtoList(result);
    }

    public Optional<DtoT> getById(Integer id) {
        Optional<ModelT> result = repository.findById(id);
        if (result.isEmpty()) throw new RequestedObjectNotFoundException(
                String.format(
                        "Requested object with id [%d] does not exist",
                        id)
        );
        return toDtoOptional(result.get());
    }

}
