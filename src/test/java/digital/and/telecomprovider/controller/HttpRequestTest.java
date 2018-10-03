package digital.and.telecomprovider.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void when_GetIsCalledOnRootPath_thenReturnHello() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getBody()).startsWith("Hello World!");
    }

    @Test
    public void when_GetIsCalledOnInvalidPath_thenReturnNotFound() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/invalidPath"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + path;
    }

}
