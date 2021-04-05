package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementMapper;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MiniprocurementService extends ServiceBase<Miniprocurement, MiniProcurementDto> {

    private final MiniprocurementRepository miniprocurementRepository;
    private final ProcurementUtils procurementUtils;

    public MiniprocurementService(RepositoryInterface<Miniprocurement> repository, MiniprocurementRepository miniprocurementRepository, ProcurementUtils procurementUtils) {
        super(repository, MiniprocurementMapper.INSTANCE);
        this.miniprocurementRepository = miniprocurementRepository;
        this.procurementUtils = procurementUtils;
    }

    public MiniProcurementDto addProcurement(MiniProcurementDto dto) {
        Miniprocurement procurement = procurementUtils.convertFromDtoToProcurement(dto);
        procurement.setTimeAdded(new Timestamp(System.currentTimeMillis()));
        return procurementUtils.convertFromProcurementToDto(
                miniprocurementRepository.save(procurement)
        );
    }

    public MiniProcurementDto updateProcurement(Integer id, MiniProcurementDto dto) {
        Optional<Miniprocurement> optionalProcurement = miniprocurementRepository.findById(id);
        if (optionalProcurement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Procurement with id [%d] does not exist", id));
        }
        Miniprocurement procurement = procurementUtils.convertFromDtoToProcurement(dto);
        procurement.setProcurementId(id);
        procurement.setTimeAdded(dto.getTimeAdded());
        return procurementUtils.convertFromProcurementToDto(
                miniprocurementRepository.save(procurement)
        );
    }

    @Deprecated
    public void deleteProcurement(Integer id) {
        miniprocurementRepository.deleteById(id);
    }
}
