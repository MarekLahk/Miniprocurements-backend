package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ContractDto;
import ee.taltech.procurementSystemBackend.models.model.Contract;
import ee.taltech.procurementSystemBackend.models.search.ContractSearch;
import ee.taltech.procurementSystemBackend.service.ContractService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/contracts")
public class ContractController extends ControllerBase<Contract, ContractDto, ContractSearch>{

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        super(contractService);
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDto addContract(@Valid @RequestBody ContractDto dto,
                                             Authentication authentication) {
        return contractService.addContract(dto, authentication);
    }

    @PutMapping("{id}")
    public ContractDto updateContract(@PathVariable Integer id,
                                                @Valid @RequestBody ContractDto dto,
                                                Authentication authentication) {
        return contractService.updateContract(id, dto, authentication);
    }

    @PatchMapping("{id}")
    public ContractDto patchContractStatus(@PathVariable Integer id,
                                                         @RequestBody ContractDto dto,
                                                     Authentication authentication) {
        return contractService.patchContractStatus(id, dto, authentication);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteContract(@PathVariable Integer id) {
        contractService.deleteContract(id);
    }
}
