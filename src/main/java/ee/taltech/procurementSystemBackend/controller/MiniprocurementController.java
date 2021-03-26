package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.service.MiniprocurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/procurement")
public class MiniprocurementController {

    private final MiniprocurementService miniprocurementService;

    @GetMapping("{id}")
    public Miniprocurement getMiniprocurementById(Integer id) {
        return  miniprocurementService.getMiniprocurementById(id);
    }

    @GetMapping
    public List<Miniprocurement> getAllMiniprocurements() {
        return miniprocurementService.getAllMiniprocurements();
    }
}
