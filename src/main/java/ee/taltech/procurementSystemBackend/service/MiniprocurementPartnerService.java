package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.MiniprocurementPartnerException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.MiniprocurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.MiniprocurementPartnerMapper;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementPartnerRepository;
import org.springframework.stereotype.Service;

@Service
public class MiniprocurementPartnerService extends ServiceBase<MiniprocurementPartner, MiniprocurementPartnerDto> {

    private final MiniprocurementPartnerRepository miniprocurementPartnerRepository;

    public MiniprocurementPartnerService(MiniprocurementPartnerRepository miniprocurementPartnerRepository) {
        super(miniprocurementPartnerRepository, MiniprocurementPartnerMapper.INSTANCE);
        this.miniprocurementPartnerRepository = miniprocurementPartnerRepository;
    }

    public MiniprocurementPartnerDto addMiniprocurementPartner(MiniprocurementPartnerDto dto) {
        MiniprocurementPartner miniprocurementPartner = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementPartnerException("No MiniprocurementPartner dto provided"));

        return toDtoOptional(miniprocurementPartnerRepository.save(miniprocurementPartner))
                .orElseThrow(() -> new MiniprocurementPartnerException("Could not save MiniprocurementPartner"));
    }

    //LINK ID needs to be given with request TODO: document this fact
    public MiniprocurementPartnerDto updateMiniprocurementPartner(Integer id, MiniprocurementPartnerDto dto) {
        if (miniprocurementPartnerRepository.findById(id).isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("MiniprocurementPartner with id [%d] does not exist", id));
        }
        MiniprocurementPartner miniprocurementPartner = toModelOptional(dto)
                .orElseThrow(() -> new MiniprocurementPartnerException("No MiniprocurementPartner dto provided"));
        miniprocurementPartner.setMiniprocurementPartnerId(id);
        return toDtoOptional(miniprocurementPartnerRepository.save(miniprocurementPartner))
                .orElseThrow(() -> new MiniprocurementPartnerException("Could not update MiniprocurementPartner"));
    }

    public void deleteMiniprocurementPartner(Integer id) {
        miniprocurementPartnerRepository.deleteById(id);
    }
}
