package com.balionis.spring5.restful;

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

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
@WebAppConfiguration
public class MyControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MyService serviceMock;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        Mockito.when(serviceMock.echo(Mockito.anyString())).thenReturn("myService:myMsg1");
    }

    @Test
    public void testMe() throws Exception {
        mockMvc.perform(get("/echo?message=myMsg"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("myService:myMsg1")));
    }
}
