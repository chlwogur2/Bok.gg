package choi.bok.gg.global.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // MockMvc 타입 빈 등록
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void homePageMockMvc() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("home/home"))
                .andDo(print());
    }

    @Test
    void homePageWebTestClient() {
        webTestClient.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "text/html;charset=UTF-8")
                .expectBody().consumeWith(System.out::println);
    }

    @Test
    void homePageTestRestTemplate() {
        String result = testRestTemplate.getForObject("/", String.class);
        System.out.println("result= " + result);
    }
}