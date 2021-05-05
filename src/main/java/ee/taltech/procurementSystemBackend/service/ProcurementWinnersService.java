package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ProcurementWinnersException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnersDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementWinnersMapper;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.models.model.ProcurementWinners;
import ee.taltech.procurementSystemBackend.repository.*;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcurementWinnersService extends ServiceBase<ProcurementWinners, ProcurementWinnersDto> {

    private final ProcurementWinnersRepository procurementWinnersRepository;
    private final ProcurementPartnerRepository procurementPartnerRepository;
    private final PocurementRepository pocurementRepository;
    private final BidRepository bidRepository;
    private final AuthUtils authUtils;

    public ProcurementWinnersService(RepositoryInterface<ProcurementWinners> repository,
                                     ProcurementWinnersRepository procurementWinnersRepository,
                                     ProcurementPartnerRepository procurementPartnerRepository,
                                     PocurementRepository pocurementRepository,
                                     BidRepository bidRepository,
                                     AuthUtils authUtils) {
        super(repository, ProcurementWinnersMapper.INSTANCE);
        this.procurementWinnersRepository = procurementWinnersRepository;
        this.procurementPartnerRepository = procurementPartnerRepository;
        this.pocurementRepository = pocurementRepository;
        this.bidRepository = bidRepository;
        this.authUtils = authUtils;
    }

    public ProcurementWinnersDto addProcurementWinner(ProcurementWinnersDto dto, Authentication authentication) {
        ProcurementPartner procurementPartner = procurementPartnerRepository
                .findByProcurementIdAndPartnerId(
                        dto.getProcurementId(), dto.getWinnerId()
                ).orElseThrow(() -> new ProcurementWinnersException("No such procurement partner"));
        // todo Handle contract procurement case

        // Is present is de-facto checked in miniprocurement partner check
        Procurement procurement = pocurementRepository.findById(dto.getProcurementId()).get();
        if (procurement.getStatus() != 2) {
            throw new ProcurementWinnersException("It is possible to set winner only to active procurement");
        }
        if (bidRepository.findFirstByLinkIdAndBidStatus(procurementPartner.getLinkId(), 2).isEmpty()) {
            throw new ProcurementWinnersException("There is no suitable bid with given id");
        }
        // disabled for testing
//        if (procurement.getDeadline().after(new Timestamp(System.currentTimeMillis()))) {
//            throw new ProcurementWinnersException("Cannot set procurement winner before deadline");
//        }

        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        // dto cannot be null, checked with @NotNull annoation in controller
        LocalDateTime decisionTime = LocalDateTime.now();
        ProcurementWinners winner = toModelOptional(dto).get();
        winner.setJudgeId(creatorId);
        winner.setCreatedAt(decisionTime);

        procurement.setStatus((short) 3);

        pocurementRepository.save(procurement);

        // todo Send Emails

        return toDtoOptional(procurementWinnersRepository.save(winner)).get();
    }
}

