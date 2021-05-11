package ee.taltech.procurementSystemBackend.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@mail.com", password = "test", roles = "group1")
public class ControllerGetMappingIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static final String ANNOUNCEMENTS = "/api/announcements";
    private static final String PROCUREMENTS = "/api/procurements";
    private static final String CONTRACTS = "/api/contracts";
    private static final String CONTRACT_PARTNERS = "/api/contractPartners";
    private static final String PROCUREMENT_PARTNERS = "/api/procurementPartners";
    private static final String PROCUREMENT_WINNERS = "/api/procurement-winners";
    private static final String QUESTIONS = "/api/questions";
    private static final String REPLIES = "/api/replies";
    private static final String BIDS = "/api/bids";

    @Test
    void getAnnouncementsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ANNOUNCEMENTS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(ANNOUNCEMENTS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContractsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(CONTRACTS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(CONTRACTS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getContractPartnersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(CONTRACT_PARTNERS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(CONTRACT_PARTNERS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProcurementsPartnersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_PARTNERS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_PARTNERS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProcurementWinnersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_WINNERS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_WINNERS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getQuestionsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(QUESTIONS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(QUESTIONS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRepliesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REPLIES).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(REPLIES + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBidsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(BIDS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(BIDS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProcurementsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENTS).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENTS + "/0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
