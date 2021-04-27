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

@SpringBootTest
public class BidUtilsStatusPatchSetActiveTest {

    @MockBean
    private BidRepository bidRepository;

    private BidUtils bidUtils;

    @BeforeEach
    void setUp() {
        bidUtils = new BidUtils(bidRepository);
    }

    @Test
    void checkBidBeforeSetToActiveShouldFailBidIsAlreadyActive() {
        Bid bid = new Bid();
        bid.setBidStatus(2);
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToActive(bid, UUID.randomUUID()))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Bid is already active");
    }

    @Test
    void checkBidBeforeSetToActiveShouldFailActiveBidIsAlreadyPresent() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByBidderLinkIdAndBidStatus(uuid, 2))
                .thenReturn(Optional.of(new Bid()));
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToActive(bid, uuid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("There can be maximally one active bid per procurement partner.");
    }

    @Test
    void checkBidBeforeSetToActiveShouldFailBidValueNull() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        bid.setDescription("test");
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByBidderLinkIdAndBidStatus(uuid, 2))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToActive(bid, uuid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Bid value cannot be null when setting to active");
    }

    @Test
    void checkBidBeforeSetToActiveShouldFailBidValueNuBlank() {
        Bid bid = new Bid();
        bid.setBidStatus(1);
        bid.setDescription("test");
        bid.setBidValue(0L);
        UUID uuid = UUID.randomUUID();
        when(bidRepository.findFirstByBidderLinkIdAndBidStatus(uuid, 2))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> bidUtils.checkBidBeforeSetToActive(bid, uuid))
                .isInstanceOf(BidException.class)
                .hasMessageContaining("Bid value must be higher than 0 when setting to active");
    }
}
