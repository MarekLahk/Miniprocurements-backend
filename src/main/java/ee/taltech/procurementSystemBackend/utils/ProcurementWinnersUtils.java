package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.ProcurementException;
import ee.taltech.procurementSystemBackend.exception.ProcurementWinnersException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementWinnersDto;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import ee.taltech.procurementSystemBackend.repository.PocurementRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurementPartnerRepository;
import ee.taltech.procurementSystemBackend.repository.ProcurerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcurementWinnersUtils {

    private final ProcurementPartnerRepository procurementPartnerRepository;
    private final PocurementRepository procurementRepository;
    private final ProcurerRepository procurerRepository;
    private final BidRepository bidRepository;

    public void checkProcurementWinner(ProcurementWinnersDto dto) {
        ProcurementPartner procurementPartner = procurementPartnerRepository
                .findByProcurementIdAndPartnerId(
                        dto.getProcurementId(), dto.getWinnerId()
                ).orElseThrow(() -> new ProcurementWinnersException("No such procurement partner"));
        // todo Handle contract procurement case
        if (bidRepository.findFirstByLinkIdAndBidStatus(procurementPartner.getLinkId(), 2).isEmpty()) {
            throw new ProcurementWinnersException("There is no suitable bid with given id");
        }
    }

    public Procurement getAndCheckProcurementToSetWinner(Integer procurementId, Integer employeeId) {
        Procurement procurement = procurementRepository.findById(procurementId)
                .orElseThrow(() -> new ProcurementException(
                        String.format(
                                "Procurement with id [%d] does not exist",
                                procurementId)));
        if (procurerRepository.findByProcurementIdAndAndEmployeeId(procurementId, employeeId).isEmpty()) {
            throw new ProcurementWinnersException("This person does not have permission to set winner to this procurement");
        }
        if (procurement.getStatus() != 2) {
            throw new ProcurementWinnersException("It is possible to set winner only to active procurement");
        }
        // todo Enable in production, disabled for easier testing
//        if (procurement.getDeadline().after(new Timestamp(System.currentTimeMillis()))) {
//            throw new ProcurementWinnersException("Cannot set procurement winner before deadline");
//        }
        return procurement;
    }
}
