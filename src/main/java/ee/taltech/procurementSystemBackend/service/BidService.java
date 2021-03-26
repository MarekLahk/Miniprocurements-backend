package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
}
