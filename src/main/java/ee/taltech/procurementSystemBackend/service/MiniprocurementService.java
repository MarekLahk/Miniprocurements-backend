package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.MiniprocurementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MiniprocurementService extends ServiceBase<Miniprocurement, MiniProcurementDto> {

    private final MiniprocurementRepository miniprocurementRepository;

    public MiniprocurementService(RepositoryInterface<Miniprocurement> repository, MiniprocurementRepository miniprocurementRepository) {
        super(repository, MiniprocurementMapper.INSTANCE);
        this.miniprocurementRepository = miniprocurementRepository;
    }

    public MiniProcurementDto addProcurement(MiniProcurementDto dto) {
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));
        procurement.setTimeAdded(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not save procurement"));
    }

    public MiniProcurementDto updateProcurement(Integer id, MiniProcurementDto dto) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", id));
        }
        Miniprocurement procurement = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementException("No procurement dto provided"));
        procurement.setProcurementId(id);
        procurement.setTimeAdded(dto.getTimeAdded());
        return toDtoOptional(miniprocurementRepository.save(procurement))
                .orElseThrow(() -> new MiniprocurementException("Could not update procurement"));
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        miniprocurementRepository.deleteById(id);
    }
}
