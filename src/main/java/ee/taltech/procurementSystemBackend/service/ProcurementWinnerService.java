package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementWinnerMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinner;
import ee.taltech.procurementSystemBackend.repository.*;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementWinnersUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcurementWinnerService extends ServiceBase<ProcurementWinner, ProcurementWinnerDto> {

    private final ProcurementWinnerRepository procurementWinnerRepository;
    private final ProcurementRepository procurementRepository;
    private final AuthUtils authUtils;
    private final ProcurementWinnersUtils procurementWinnersUtils;

    public ProcurementWinnerService(RepositoryInterface<ProcurementWinner> repository,
                                    ProcurementWinnerRepository procurementWinnerRepository,
                                    ProcurementRepository procurementRepository,
                                    AuthUtils authUtils, ProcurementWinnersUtils procurementWinnersUtils) {
        super(repository, ProcurementWinnerMapper.INSTANCE);
        this.procurementWinnerRepository = procurementWinnerRepository;
        this.procurementRepository = procurementRepository;
        this.authUtils = authUtils;
        this.procurementWinnersUtils = procurementWinnersUtils;
    }

    public ProcurementWinnerDto addProcurementWinner(ProcurementWinnerDto dto, Authentication authentication) {
        if (dto.getWinnerId() != null) {
            procurementWinnersUtils.checkProcurementWinner(dto);
        }

        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        Procurement procurement = procurementWinnersUtils.getAndCheckProcurementToSetWinner(dto.getProcurementId(), creatorId);

        LocalDateTime decisionTime = LocalDateTime.now();
        // dto cannot be null, checked with @NotNull annotation in controller
        ProcurementWinner winner = toModelOptional(dto).get();
        winner.setJudgeId(creatorId);
        winner.setCreatedAt(decisionTime);
        winner.setUpdatedAt(decisionTime);
        procurement.setStatus((short) 3);

        procurementRepository.save(procurement);

        // todo Send Emails

        return toDtoOptional(procurementWinnerRepository.save(winner)).get();
    }
}

