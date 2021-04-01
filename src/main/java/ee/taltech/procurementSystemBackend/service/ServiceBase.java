package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.search.SearchObject;
import ee.taltech.procurementSystemBackend.model.search.SearchSpecPack;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class ServiceBase<T> {

    private final RepositoryInterface<T> repository;

    public List<T> getByParams(SearchObject<T> searchObject) {
        SearchSpecPack<T> searchSpec = searchObject.getSearchSpec();
        return repository.findAll(searchSpec.getSpecification(),  searchSpec.getPageable()).getContent();
    }

    public Optional<T> getById(Integer id) {
        return repository.findById(id);
    }

}
