package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
import ee.taltech.procurementSystemBackend.models.Dto.MiniprocurementPartnerDto;
import ee.taltech.procurementSystemBackend.models.search.MiniprocurementPartnerSearch;
import ee.taltech.procurementSystemBackend.service.MiniprocurementPartnerService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("api/miniprocurementPartners")
@Tag(name = "miniprocurementPartners", description = "Link 0 or 1 miniprocurement and 0 or more partners", externalDocs = @ExternalDocumentation(url = "https://gitlab.cs.ttu.ee/taltech-uurimisryhmad/riigihanked/small-procurement-system-backend/-/issues/22", description = "Gitlab issue about creation of this API."))
public class MiniprocurementPartnerController extends ControllerBase<MiniprocurementPartner, MiniprocurementPartnerDto, MiniprocurementPartnerSearch> {

    private final MiniprocurementPartnerService miniprocurementPartnerService;

    public MiniprocurementPartnerController(MiniprocurementPartnerService miniprocurementPartnerService) {
        super(miniprocurementPartnerService, MiniprocurementPartner.class, MiniprocurementPartnerDto.class);
        this.miniprocurementPartnerService = miniprocurementPartnerService;
    }

    @Operation(summary = "Add a new procurement-partner link", description = "Add a new miniprocurement partner to miniprocurement link.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MiniprocurementPartnerDto.class)))),
            @ApiResponse(responseCode = "500", description = "internal server error, procurement or partner might not exist or a link like this may already exist", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"timestamp\": \"2021-04-24T09:24:56.958+00:00\",\n" +
                    "    \"status\": 500,\n" +
                    "    \"error\": \"Internal Server Error\",\n" +
                    "    \"message\": \"\",\n" +
                    "    \"path\": \"/api/miniprocurementPartners\"\n" +
                    "}")))
    })
    @PostMapping(produces = {"application/json"})
    public MiniprocurementPartnerDto addMiniprocurementPartner(@Valid @RequestBody MiniprocurementPartnerDto dto) {
        return miniprocurementPartnerService.addMiniprocurementPartner(dto);
    }

    @PutMapping("{id}")
    public MiniprocurementPartnerDto updateMiniprocurementPartner(@PathVariable Integer id, @RequestBody MiniprocurementPartnerDto dto) {
        return miniprocurementPartnerService.updateMiniprocurementPartner(id, dto);
    }

    //TODO: Add checks on deleting a partner
    @Operation(summary = "Delete a procurement-partner link. This should only be done if related procurement is in draft. Currently this is not checked.")
    @DeleteMapping("{id}")
    public void deleteMiniprocurementPartner(@PathVariable Integer id) {
        miniprocurementPartnerService.deleteMiniprocurementPartner(id);
    }

}