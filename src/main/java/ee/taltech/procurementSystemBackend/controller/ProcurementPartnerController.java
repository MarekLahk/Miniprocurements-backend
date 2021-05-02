package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.ProcurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.models.search.ProcurementPartnerSearch;
import ee.taltech.procurementSystemBackend.service.ProcurementPartnerService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/procurementPartners")
@Tag(name = "procurementPartners", description = "Link 0 or 1 procurement and 0 or more partners", externalDocs = @ExternalDocumentation(url = "https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/issues/22", description = "Gitlab issue about creation of this API."))
public class ProcurementPartnerController extends ControllerBase<ProcurementPartner, ProcurementPartnerDto, ProcurementPartnerSearch> {

    private final ProcurementPartnerService procurementPartnerService;

    public ProcurementPartnerController(ProcurementPartnerService procurementPartnerService) {
        super(procurementPartnerService, ProcurementPartner.class, ProcurementPartnerDto.class);
        this.procurementPartnerService = procurementPartnerService;
    }

    @Operation(summary = "Add a new procurement-partner link", description = "Add a new procurement partner to procurement link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProcurementPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "internal server error, procurement or partner might not exist or a link like this may already exist", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/procurementPartners\"\n" +
                    "}")))
    })
    @PostMapping(produces = {"application/json"})
    public ProcurementPartnerDto addProcurementPartner(@Valid @RequestBody ProcurementPartnerDto dto) {
        return procurementPartnerService.addProcurementPartner(dto);
    }

    @Operation(summary = "Change a procurement-partner link", description = "Change an existing procurement partner to procurement link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProcurementPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "you might have omitted the procurementPartnerLinkId value", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/procurementPartners\"\n" +
                    "}")))
    })
    @Parameter(name="id", example="2", description="procurement and partner connection id: procurementPartnerId")
    @PutMapping("{id}")
    public ProcurementPartnerDto updateProcurementPartner(@PathVariable Integer id, @RequestBody ProcurementPartnerDto dto) {
        return procurementPartnerService.updateProcurementPartner(id, dto);
    }

    //TODO: Add checks on deleting a partner
    @Operation(summary = "Delete a procurement-partner link. This should only be done if related procurement is in draft. Currently this is not checked.")
    @DeleteMapping("{id}")
    @Parameter(name="id", example="2", description="Procurement and partner connection id: procurementPartnerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProcurementPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "unexpected server error occurred - perhaps a link with that id does not exist", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/procurementPartners\"\n" +
                    "}")))
    })
    public void deleteProcurementPartner(@PathVariable Integer id) {
        procurementPartnerService.deleteProcurementPartner(id);
    }

}