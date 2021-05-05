package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnersDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementWinnersMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinners;
import ee.taltech.procurementSystemBackend.repository.*;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import ee.taltech.procurementSystemBackend.utils.ProcurementWinnersUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcurementWinnersService extends ServiceBase<ProcurementWinners, ProcurementWinnersDto> {

    private final ProcurementWinnersRepository procurementWinnersRepository;
    private final PocurementRepository pocurementRepository;
    private final AuthUtils authUtils;
    private final ProcurementWinnersUtils procurementWinnersUtils;

    public ProcurementWinnersService(RepositoryInterface<ProcurementWinners> repository,
                                     ProcurementWinnersRepository procurementWinnersRepository,
                                     PocurementRepository pocurementRepository,
                                     AuthUtils authUtils, ProcurementWinnersUtils procurementWinnersUtils) {
        super(repository, ProcurementWinnersMapper.INSTANCE);
        this.procurementWinnersRepository = procurementWinnersRepository;
        this.pocurementRepository = pocurementRepository;
        this.authUtils = authUtils;
        this.procurementWinnersUtils = procurementWinnersUtils;
    }

    public ProcurementWinnersDto addProcurementWinner(ProcurementWinnersDto dto, Authentication authentication) {
        if (dto.getWinnerId() != null) {
            procurementWinnersUtils.checkProcurementWinner(dto);
        }

        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        Procurement procurement = procurementWinnersUtils.getAndCheckProcurementToSetWinner(dto.getProcurementId());

        LocalDateTime decisionTime = LocalDateTime.now();
        // dto cannot be null, checked with @NotNull annotation in controller
        ProcurementWinners winner = toModelOptional(dto).get();
        winner.setJudgeId(creatorId);
        winner.setCreatedAt(decisionTime);
        winner.setUpdatedAt(decisionTime);
        procurement.setStatus((short) 3);

        pocurementRepository.save(procurement);

        // todo Send Emails

        return toDtoOptional(procurementWinnersRepository.save(winner)).get();
    }
}

