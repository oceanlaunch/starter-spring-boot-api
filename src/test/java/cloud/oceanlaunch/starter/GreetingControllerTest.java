package cloud.oceanlaunch.starter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void saysHello() throws Exception {
        mvc.perform(get("/api/hello").param("name", "Asha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Asha!"));
    }

    @Test
    void keepsGreetingsAndRefusesEmptyOnes() throws Exception {
        mvc.perform(post("/api/greetings").contentType(MediaType.APPLICATION_JSON).content("{\"message\":\"First!\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("First!"));
        mvc.perform(post("/api/greetings").contentType(MediaType.APPLICATION_JSON).content("{\"message\":\" \"}"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/greetings")).andExpect(jsonPath("$[0].message").value("First!"));
    }

    @Test
    void isHealthy() throws Exception {
        mvc.perform(get("/actuator/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));
    }
}
