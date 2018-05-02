package com.balionis.card;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;

import java.nio.charset.Charset;
import javax.servlet.Filter;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.balionis.card.repository.CardRepository;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardApp.class)
@WebAppConfiguration
public class CardControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    private CardRepository repositoryMock;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).addFilters(springSecurityFilterChain).build();
    }

    @Test
    @WithMockUser(username = "card1", password = "1234", roles = "USER")
    public void testWithdraw() throws Exception {

        CardRequest req = new CardRequest("card1", -100.0);

        Mockito.when(repositoryMock.update(req)).thenReturn(900.0);

        mockMvc.perform(get("/withdraw?amount=100").with(httpBasic("card1","1234")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("card1")))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.balance", closeTo(900.0, 0.01)));
    }

    @Test
    @WithMockUser(username = "card1", password = "1234", roles = "USER")
    public void testWithdrawNegative() throws Exception {

        // NOOP:
        // CardRequest req = new CardRequest("card1", -100.0);
        // Mockito.when(repositoryMock.update(req)).thenReturn(1100.0);

        mockMvc.perform(get("/withdraw?amount=-100").with(httpBasic("card1","1234")))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("card1")))
                .andExpect(jsonPath("$.status", is("FAILURE")))
                .andExpect(jsonPath("$.reason", is("withdraw amount must be positive")));
    }

    @Test
    @WithMockUser(username = "card1", password = "1234", roles = "USER")
    public void testNoFunds() throws Exception {

        CardRequest req = new CardRequest("card1", -100.0);

        Mockito.when(repositoryMock.update(req)).thenThrow(
                new CardException("card1", 99.0, "not enough founds")
        );

        mockMvc.perform(get("/withdraw?amount=100").with(httpBasic("card1","1234")))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("card1")))
                .andExpect(jsonPath("$.status", is("FAILURE")))
                .andExpect(jsonPath("$.balance", closeTo(99.0, 0.01)));
    }

    @Test
    @WithMockUser(username = "card1", password = "234", roles = "USER")
    public void testBadPin() throws Exception {

        CardRequest req = new CardRequest("card1", -100.0);

        Mockito.when(repositoryMock.update(req)).thenReturn(900.0);

        mockMvc.perform(get("/withdraw?amount=100").with(httpBasic("card1","234")))
                .andExpect(status().is(401));

    }

    @Test
    @WithMockUser(username = "card3", password = "1234", roles = "USER")
    public void testBadCard() throws Exception {

        CardRequest req = new CardRequest("card3", -100.0);

        Mockito.when(repositoryMock.update(req)).thenReturn(900.0);

        mockMvc.perform(get("/withdraw?amount=100").with(httpBasic("card3","1234")))
                .andExpect(status().is(401));

    }

    @Test
    public void testTopUp() throws Exception {

        CardRequest req = new CardRequest("card1", 100.0);

        Mockito.when(repositoryMock.update(req)).thenReturn(1100.0);

        mockMvc.perform(get("/topup?cardName=card1&amount=100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("card1")))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.balance", closeTo(1100.0, 0.01)));
    }

    @Test
    public void testTopUpNegative() throws Exception {

        mockMvc.perform(get("/topup?cardName=card1&amount=-100"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("card1")))
                .andExpect(jsonPath("$.status", is("FAILURE")))
                .andExpect(jsonPath("$.reason", is("top-up amount must be positive")));
    }

    @Test
    public void testTopUpBadCard() throws Exception {

        CardRequest req = new CardRequest("badcard", 100.0);

        Mockito.when(repositoryMock.update(req)).thenThrow(
                new CardException("badcard", -1, "unknown cardName")
        );

        mockMvc.perform(get("/topup?cardName=badcard&amount=100"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.cardName", is("badcard")))
                .andExpect(jsonPath("$.status", is("FAILURE")));
    }

}
