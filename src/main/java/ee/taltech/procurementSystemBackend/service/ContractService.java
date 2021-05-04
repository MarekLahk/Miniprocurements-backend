package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.ContractException;
import ee.taltech.procurementSystemBackend.models.Dto.ContractDto;
import ee.taltech.procurementSystemBackend.models.mapper.ContractMapper;
import ee.taltech.procurementSystemBackend.models.model.Contract;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.ContractRepository;
import ee.taltech.procurementSystemBackend.repository.RepositoryInterface;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContractService extends ServiceBase<Contract, ContractDto> {

    private final ContractRepository contractRepository;
    private final AuthUtils authUtils;

    public ContractService(RepositoryInterface<Contract> repository,
                           ContractRepository contractRepository,
                           AuthUtils authUtils) {
        super(repository, ContractMapper.INSTANCE);
        this.contractRepository = contractRepository;
        this.authUtils = authUtils;
    }

    public ContractDto addContract(ContractDto dto, Authentication authentication) {
        Contract contract = toModelOptional(dto)
                .orElseThrow(() -> new ContractException("No contract dto provided"));
        Integer creatorId = authUtils.getPersonToPerformOperations(authentication).getId();
        return toDtoOptional(contractRepository.save(contract))
                .orElseThrow(() -> new ContractException("Could not save contract"));
    }

    public ContractDto updateContract(Integer id,
                                                ContractDto dto,
                                                Authentication authentication) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        // get this to take some values that cannot be passed by put method ,
        Contract initialContract = optionalContract.get();

        Contract contract = toModelOptional(dto)
                .orElseThrow(() -> new ContractException("No contract dto provided"));

        return toDtoOptional(contractRepository.save(contract))
                .orElseThrow(() -> new ContractException("Could not update contract"));
    }

    public ContractDto patchContractStatus(Integer id, ContractDto dto, Authentication authentication) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        Person person = authUtils.getPersonToPerformOperations(authentication);

        // optional isPresent is checked in utils
        Contract contract = optionalContract.get();
        return toDtoOptional(contractRepository.save(contract)).get();
    }

    @Deprecated
    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}
