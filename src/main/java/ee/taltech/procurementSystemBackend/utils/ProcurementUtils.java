package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.model.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.model.Miniprocurement;
import org.springframework.stereotype.Component;

@Component
public class ProcurementUtils {

    public Miniprocurement convertFromDtoToProcurement(MiniProcurementDto procurementDto) {
        return Miniprocurement.builder()
                .procurementName(procurementDto.getProcurementName())
                .amount(procurementDto.getAmount())
                .description(procurementDto.getDescription())
                .requirements(procurementDto.getRequirements())
                .contractId(procurementDto.getContractId())
                .timeAdded(procurementDto.getTimeAdded())
                .addedBy(procurementDto.getAddedBy())
                .deadline(procurementDto.getDeadline())
                .status(procurementDto.getStatus()).build();
    }

    public MiniProcurementDto convertFromProcurementToDto(Miniprocurement procurement) {
        return MiniProcurementDto.builder()
                .procurementName(procurement.getProcurementName())
                .amount(procurement.getAmount())
                .description(procurement.getDescription())
                .requirements(procurement.getRequirements())
                .contractId(procurement.getContractId())
                .timeAdded(procurement.getTimeAdded())
                .addedBy(procurement.getAddedBy())
                .deadline(procurement.getDeadline())
                .status(procurement.getStatus())
                .timeFinished(procurement.getTimeFinished()).build();
    }
}
