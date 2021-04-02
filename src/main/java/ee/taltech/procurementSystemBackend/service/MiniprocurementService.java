package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MiniprocurementService {

    private final MiniprocurementRepository miniprocurementRepository;
    private final ProcurementUtils procurementUtils;

    public MiniProcurementDto getMiniprocurementById(Integer id) {
        return miniprocurementRepository.findById(id).map(
                procurementUtils::convertFromProcurementToDto)
                .orElseThrow(() ->
                        new RequestedObjectNotFoundException(
                                String.format("Procurement with id [%d] does not exist", id))
                        );
    }

    public List<MiniProcurementDto> getAllMiniprocurements() {
        return miniprocurementRepository.findAll().stream()
                .map(procurementUtils::convertFromProcurementToDto)
                .collect(Collectors.toList());
    }

    public List<MiniProcurementDto> getMiniprocurementByCreator(Integer creatorId) {
        return miniprocurementRepository.findMiniprocurementByAddedBy(creatorId).stream()
                .map(procurementUtils::convertFromProcurementToDto)
                .collect(Collectors.toList());
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
