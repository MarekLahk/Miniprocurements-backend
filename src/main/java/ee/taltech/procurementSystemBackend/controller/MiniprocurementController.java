package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.model.Dto.MiniProcurementDto;
import ee.taltech.procurementSystemBackend.service.MiniprocurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/procurement")
public class MiniprocurementController {

    private final MiniprocurementService miniprocurementService;

    @GetMapping("{id}")
    public MiniProcurementDto getMiniprocurementById(@PathVariable Integer id) {
        return miniprocurementService.getMiniprocurementById(id);
    }

    @GetMapping
    public List<MiniProcurementDto> getAllMiniprocurements() {
        return miniprocurementService.getAllMiniprocurements();
    }

    @GetMapping("/employee/{creatorId}")
    public List<MiniProcurementDto> getAllMiniprocurementsByCreator(@PathVariable Integer creatorId) {
        return miniprocurementService.getMiniprocurementByCreator(creatorId);
    }

    @PostMapping
    public MiniProcurementDto addProcurement(@RequestBody MiniProcurementDto dto) {
        return miniprocurementService.addProcurement(dto);
    }
}
