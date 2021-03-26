package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.utils.ProcurementUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MiniprocurementService {

    private final MiniprocurementRepository miniprocurementRepository;
    private final ProcurementUtils procurementUtils;

    public MiniProcurementDto getMiniprocurementById(Integer id) {
        return procurementUtils.convertFromProcurementToDto(miniprocurementRepository.findByProcurementId(id));
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
}
