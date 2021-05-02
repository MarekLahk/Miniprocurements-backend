package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ProcurementPartnerException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.ProcurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.ProcurementPartnerMapper;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.repository.ProcurementPartnerRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcurementPartnerService extends ServiceBase<ProcurementPartner, ProcurementPartnerDto> {

    private final ProcurementPartnerRepository procurementPartnerRepository;

    public ProcurementPartnerService(ProcurementPartnerRepository procurementPartnerRepository) {
        super(procurementPartnerRepository, ProcurementPartnerMapper.INSTANCE);
        this.procurementPartnerRepository = procurementPartnerRepository;
    }

    public ProcurementPartnerDto addProcurementPartner(ProcurementPartnerDto dto) {
        ProcurementPartner procurementPartner = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementPartnerException("No ProcurementPartner dto provided"));
        procurementPartner.setCreatedAt(null);
        return toDtoOptional(procurementPartnerRepository.save(procurementPartner))
                .orElseThrow(() -> new ProcurementPartnerException("Could not save ProcurementPartner"));
    }

    //LINK ID needs to be given with request TODO: document this fact
    public ProcurementPartnerDto updateProcurementPartner(Integer id, ProcurementPartnerDto dto) {
        ProcurementPartner procurementPartnerOld = procurementPartnerRepository.findById(id)
                .orElseThrow(() -> new RequestedObjectNotFoundException(
                String.format("ProcurementPartner with id [%d] does not exist", id)));
        ProcurementPartner procurementPartner = toModelOptional(dto)
                .orElseThrow(() -> new ProcurementPartnerException("No ProcurementPartner dto provided"));
        procurementPartner.setId(id);
        procurementPartner.setCreatedAt(procurementPartnerOld.getCreatedAt());
        return toDtoOptional(procurementPartnerRepository.save(procurementPartner))
                .orElseThrow(() -> new ProcurementPartnerException("Could not update ProcurementPartner"));
    }

    public void deleteProcurementPartner(Integer id) {
        procurementPartnerRepository.deleteById(id);
    }
}
