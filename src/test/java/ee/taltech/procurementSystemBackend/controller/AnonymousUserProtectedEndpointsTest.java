package ee.taltech.procurementSystemBackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnonymousUserProtectedEndpointsTest {

    @Autowired
    private MockMvc mvc;

    private static final String ANNOUNCEMENTS = "/api/announcements";
    private static final String PROCUREMENTS = "/api/procurements";
    private static final String PROCUREMENT_PARTNERS = "/api/miniprocurementPartners";
    private static final String QUESTIONS = "/api/questions";
    private static final String REPLIES = "/api/replies";

    @Test
    public void whenUnauthorizedGetAnnouncementsThenFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ANNOUNCEMENTS).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void whenUnauthorizedGetProcurementsThenFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENTS).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void whenUnauthorizedGetProcurementPartnersThenFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_PARTNERS).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void whenUnauthorizedGetQuestionsThenFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(QUESTIONS).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void whenUnauthorizedGetRepliesThenFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REPLIES).accept(APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}
