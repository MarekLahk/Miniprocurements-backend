package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MiniprocurementService {

    private final MiniprocurementRepository miniprocurementRepository;

    public Miniprocurement getMiniprocurementById(Integer id) {
        return miniprocurementRepository.findByProcurementId(id);
    }

    public List<Miniprocurement> getAllMiniprocurements() {
        return miniprocurementRepository.findAll();
    }
}
