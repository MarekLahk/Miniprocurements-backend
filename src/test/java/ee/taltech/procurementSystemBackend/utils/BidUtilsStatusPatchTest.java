package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.BidException;
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
public class BidUtilsStatusPatchTest {

    @MockBean
    private BidRepository bidRepository;

    private BidUtils bidUtils;

    @BeforeEach
    void setUp() {
        bidUtils = new BidUtils(bidRepository);
    }

    @Test
    void checkIncomingStatusShouldThrowError() {
        assertThatThrownBy(() -> bidUtils.checkIncomingStatus(5))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Allowed bid status codes are: [1, 2, 3]");
    }

    @Test
    void checkIncomingStatusShouldPass() {
        try {
            bidUtils.checkIncomingStatus(2);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    void checkBidBeforeSetToWaitingBidIsAlreadyWaitingShouldThrowError() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToWaiting(bid, UUID.randomUUID()))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Bid is already waiting");
    }

    @Test
    void checkBidBeforeSetToWaitingSomeAnotherBidIsAlreadyWaitingShouldThrowError() {
        Bid bid = new Bid();
        bid.setBidStatus(2);
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByBidderLinkIdAndBidStatus(uuid, 1))
                .thenReturn(Optional.of(new Bid()));
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToWaiting(bid, uuid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("There can be only one waiting bid per procurement partner.");
    }

    @Test
    void checkBidBeforeSetToWaitingShouldPass() {
        Bid bid = new Bid();
        bid.setBidStatus(2);
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByBidderLinkIdAndBidStatus(uuid, 1))
                .thenReturn(Optional.empty());
        try {
            bidUtils.checkBidBeforeSetToWaiting(bid, uuid);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
