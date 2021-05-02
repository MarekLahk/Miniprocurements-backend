package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ContractPartnerException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.ContractPartnerDto;
import ee.taltech.procurementSystemBackend.models.mapper.ContractPartnerMapper;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import ee.taltech.procurementSystemBackend.repository.ContractPartnerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ContractPartnerService extends ServiceBase<ContractPartner, ContractPartnerDto> {

    private final ContractPartnerRepository contractPartnerRepository;

    public ContractPartnerService(ContractPartnerRepository contractPartnerRepository) {
        super(contractPartnerRepository, ContractPartnerMapper.INSTANCE);
        this.contractPartnerRepository = contractPartnerRepository;
    }

    public ContractPartnerDto addContractPartner(ContractPartnerDto dto) {
        ContractPartner contractPartner = toModelOptional(dto)
                .orElseThrow(() -> new ContractPartnerException("No ContractPartner dto provided"));
        return toDtoOptional(contractPartnerRepository.save(contractPartner))
                .orElseThrow(() -> new ContractPartnerException("Could not save ContractPartner"));
    }

    //LINK ID needs to be given with request TODO: document this fact
    public ContractPartnerDto updateContractPartner(Integer id, ContractPartnerDto dto) {
        ContractPartner partner = contractPartnerRepository.findById(id)
                .orElseThrow(() -> new RequestedObjectNotFoundException(
                String.format("ContractPartner with id [%d] does not exist", id)));
        ContractPartner contractPartner = toModelOptional(dto)
                .orElseThrow(() -> new ContractPartnerException("No ContractPartner dto provided"));
        contractPartner.setContractPartnerId(id);
        return toDtoOptional(contractPartnerRepository.save(contractPartner))
                .orElseThrow(() -> new ContractPartnerException("Could not update ContractPartner"));
    }

    public void deleteContractPartner(Integer id) {
        contractPartnerRepository.deleteById(id);
    }
}
