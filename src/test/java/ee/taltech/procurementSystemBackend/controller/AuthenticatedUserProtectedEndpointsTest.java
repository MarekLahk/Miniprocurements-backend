//package ee.taltech.procurementSystemBackend.controller;
//
//import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
//import ee.taltech.procurementSystemBackend.models.model.Announcement;
//import ee.taltech.procurementSystemBackend.service.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WithMockUser(username = "test", password = "test", roles = "group1")
//public class AuthenticatedUserProtectedEndpointsTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private AnnouncementService announcementService;
//
//    @MockBean
//    private MiniprocurementService miniprocurementService;
//
//    @MockBean
//    private MiniprocurementPartnerService miniprocurementPartnerService;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private ReplyService replyService;
//
//    @MockBean
//    private ServiceBase<Announcement, AnnouncementDto> serviceBase;
//
//    private static final String ANNOUNCEMENTS = "/api/announcements";
//    private static final String PROCUREMENTS = "/api/procurements";
//    private static final String PROCUREMENT_PARTNERS = "/api/miniprocurementPartners";
//    private static final String QUESTIONS = "/api/questions";
//    private static final String REPLIES = "/api/replies";
//
//    @Test
//    public void whenAuthorizedGetAnnouncementsThenOk() throws Exception {
//        when(serviceBase.getByParams(Mockito.any())).thenReturn(List.of());
//        mvc.perform(MockMvcRequestBuilders.get(ANNOUNCEMENTS).accept(APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void whenAuthorizedGetProcurementsThenOk() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENTS).accept(APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void whenAuthorizedGetProcurementPartnersThenOk() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get(PROCUREMENT_PARTNERS).accept(APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void whenAuthorizedGetQuestionsThenOk() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get(QUESTIONS).accept(APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void whenAuthorizedGetRepliesThenOk() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get(REPLIES).accept(APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
