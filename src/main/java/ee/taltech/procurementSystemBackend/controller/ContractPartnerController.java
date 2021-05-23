package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ContractPartnerDto;
import ee.taltech.procurementSystemBackend.models.model.ContractPartner;
import ee.taltech.procurementSystemBackend.models.search.ContractPartnerSearch;
import ee.taltech.procurementSystemBackend.service.ContractPartnerService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/contractPartners")
@Tag(name = "contract-partner-controller", description = "Link 0 or 1 contract and 0 or more partners", externalDocs = @ExternalDocumentation(url = "https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/issues/27", description = "Gitlab issue about creation of this API."))
public class ContractPartnerController extends ControllerBase<ContractPartner, ContractPartnerDto, ContractPartnerSearch> {

    private final ContractPartnerService contractPartnerService;

    public ContractPartnerController(ContractPartnerService contractPartnerService) {
        super(contractPartnerService);
        this.contractPartnerService = contractPartnerService;
    }

    @Operation(summary = "Add a new contract-partner link", description = "Add a new contract partner to contract link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContractPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "internal server error, contract or partner might not exist or a link like this may already exist", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/contractPartners\"\n" +
                    "}")))
    })
    @PostMapping(produces = {"application/json"})
    public ContractPartnerDto addContractPartner(@Valid @RequestBody ContractPartnerDto dto) {
        return contractPartnerService.addContractPartner(dto);
    }

    @Operation(summary = "Change a contract-partner link", description = "Change an existing contract partner to contract link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContractPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "you might have omitted the contractPartnerLinkId value", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/contractPartners\"\n" +
                    "}")))
    })
    @Parameter(name="id", example="2", description="Contract and partner connection id: id")
    @PutMapping("{id}")
    public ContractPartnerDto updateContractPartner(@PathVariable Integer id, @RequestBody ContractPartnerDto dto) {
        return contractPartnerService.updateContractPartner(id, dto);
    }

    //TODO: Add checks on deleting a partner
    @Operation(summary = "Delete a contract-partner link. This should only be done if related contract is in draft. Currently this is not checked.")
    @DeleteMapping("{id}")
    @Parameter(name="id", example="2", description="Contract and partner connection id: id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContractPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "unexpected server error ocurred - perhaps a link with that id does not exist", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/contractPartners\"\n" +
                    "}")))
    })
    public void deleteContractPartner(@PathVariable Integer id) {
        contractPartnerService.deleteContractPartner(id);
    }

}