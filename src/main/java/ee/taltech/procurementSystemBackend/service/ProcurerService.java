package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ProcurerException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurerDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurerMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurer;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcurerService extends ServiceBase<Procurer, ProcurerDto> {

    private final ProcurerRepository procurerRepository;
    private final AuthUtils authUtils;

    public ProcurerService(RepositoryInterface<Procurer> repository,
                           ProcurerRepository procurerRepository,
                           AuthUtils authUtils) {
        super(repository, ProcurerMapper.INSTANCE);
        this.procurerRepository = procurerRepository;
        this.authUtils = authUtils;
    }

    public void addProcurer(ProcurerDto dto, Authentication authentication) {
        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        if (procurerRepository.findByProcurementIdAndAndEmployeeId(dto.getProcurementId(), creatorId).isEmpty()) {
            throw new ProcurerException(
                    String.format("Employee with id [%d] does not have permission to this procurement", creatorId)
            );
        }
        if (procurerRepository.findByProcurementIdAndAndEmployeeId(dto.getProcurementId(), dto.getEmployeeId()).isPresent()) {
            throw new ProcurerException(
                    String.format("Employee with id [%d] already has permission to this procurement", dto.getEmployeeId())
            );
        }
        // Not null is checked with @NotNull annotation
        Procurer procurer = new Procurer();
        procurer.setProcurementId(dto.getProcurementId());
        procurer.setEmployeeId(dto.getEmployeeId());
        procurer.setCreatedAt(LocalDateTime.now());
        procurer.setUpdatedAt(LocalDateTime.now());
        procurerRepository.save(procurer);
    }
}
