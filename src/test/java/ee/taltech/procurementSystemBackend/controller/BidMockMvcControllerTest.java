package ee.taltech.procurementSystemBackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.Dto.BiddingResponse;
import ee.taltech.procurementSystemBackend.service.BidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BidMockMvcControllerTest {

    @MockBean
    private BidService bidService;

    @Autowired
    private MockMvc mvc;

    private static final String BID_PATH = "/api/public/bids/519b882b-f47c-4dff-a0a5-a3b63ff4b402";
    private static final String PROTECTED_PATH = "/api/bids";
    private static final UUID RANDOM_UUID = UUID.fromString("519b882b-f47c-4dff-a0a5-a3b63ff4b402");

    @Test
    public void whenRequestBidThenStatusOk() throws Exception {
        when(bidService.getCurrentWaitingBid(RANDOM_UUID)).thenReturn(new BiddingResponse());
        mvc.perform(MockMvcRequestBuilders.get(BID_PATH).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostBidNoBodyThenBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(BID_PATH).accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenUnauthorizedGetProtectedEndpointThenStatusFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROTECTED_PATH).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "group1")
    public void whenAuthenticatedGetProtectedEndpointThenStatusFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROTECTED_PATH).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
