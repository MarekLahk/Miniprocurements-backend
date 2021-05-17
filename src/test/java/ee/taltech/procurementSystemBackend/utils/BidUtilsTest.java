package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.BidException;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import ee.taltech.procurementSystemBackend.repository.BidRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class BidUtilsTest {

    @MockBean
    private BidRepository bidRepository;

    private BidUtils bidUtils;

    @BeforeEach
    void setUp() {
        bidUtils = new BidUtils(bidRepository);
    }

    @Test
    void checkBidBeforeAddingShouldThrowException() {
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByLinkIdAndBidStatus(uuid, 1))
                .thenReturn(Optional.of(new Bid()));
        assertThatThrownBy(() -> bidUtils.checkBidBeforeAdding(uuid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("There can be maximally one waiting bid per procurement partner.");
    }

    @Test
    void checkBidBeforeAddingShouldPass() {
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByLinkIdAndBidStatus(uuid, 1))
                .thenReturn(Optional.empty());
        try {
            bidUtils.checkBidBeforeAdding(uuid);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    void getBidToUpdateNoIdProvidedShouldThrowError() {
        BidDto dto = new BidDto();
        assertThatThrownBy(() -> bidUtils.getBidToUpdate(UUID.randomUUID(), dto))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("No bid id provided in dto");
    }

    @Test
    void checkIfBidIsInactiveShouldThrowError() {
        Bid bid = new Bid();
        bid.setBidStatus(3);
        assertThatThrownBy(() -> bidUtils.checkIfBidIsInactive(bid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Inactive bid cannot be updated");
    }

    @Test
    void checkIfBidIsInactiveShouldPass() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        try {
            bidUtils.checkIfBidIsInactive(bid);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    void checkIfBidIsActiveShouldThrowError() {
        Bid bid = new Bid();
        bid.setBidStatus(2);
        assertThatThrownBy(() -> bidUtils.checkIfBidIsActive(bid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Active bid cannot be updated");
    }

    @Test
    void checkIfBidIsActiveShouldPass() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        try {
            bidUtils.checkIfBidIsActive(bid);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
