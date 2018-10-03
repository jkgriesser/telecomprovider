package digital.and.telecomprovider.controller;

import digital.and.telecomprovider.model.PhoneNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneNumberControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void when_GetIsCalledOnRootPath_thenReturnHello() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).startsWith("Hello World!");
    }

    @Test
    public void when_GetIsCalledOnInvalidPath_thenReturnNotFound() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/invalidPath"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void when_GetisCalledOnPhoneNumbersPath_thenReturnListOfPhoneNumbers() {
        ResponseEntity<List<PhoneNumber>> response = restTemplate.exchange(
                createURLWithPort("/api/phonenumbers"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                new ParameterizedTypeReference<List<PhoneNumber>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PhoneNumber> phoneNumbers = response.getBody();
        assertNotNull(phoneNumbers);
        assertFalse(phoneNumbers.isEmpty());
    }

    @Test
    public void when_GetIsCalledOnPhoneNumbersForCustomerPathWithValidId_thenReturnListOfPhoneNumbers() {
        ResponseEntity<List<PhoneNumber>> response = restTemplate.exchange(
                createURLWithPort("/api/customers/1/phonenumbers"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                new ParameterizedTypeReference<List<PhoneNumber>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PhoneNumber> phoneNumbers = response.getBody();
        assertNotNull(phoneNumbers);
        assertFalse(phoneNumbers.isEmpty());
    }

    @Test
    public void when_GetIsCalledOnPhoneNumbersForCustomerPathWithInvalidId_thenReturnNotFound() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/customers/999/phonenumbers"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void when_GetIsCalledOnPhoneNumbersForCustomerPathWithString_thenReturnBadRequest() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/customers/notAnId/phonenumbers"),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * The following tests necessitate a workaround to avoid a ResourceAccessException on PATCH requests.
     *
     * <p>The problem occurs due to Java's HttpURLConnection.
     * The workaround uses Apache HttpClient instead. See</p>
     *
     * @see <a href="https://stackoverflow.com/questions/29447382/resttemplate-patch-request">
     *     https://stackoverflow.com/questions/29447382/resttemplate-patch-request</a>
     * @see <a href="https://jira.spring.io/browse/SPR-15052">https://jira.spring.io/browse/SPR-15052</a>
     * @see <a href="https://github.com/spring-cloud/spring-cloud-netflix/issues/1777">
     *     https://github.com/spring-cloud/spring-cloud-netflix/issues/1777</a>
     */
    @Test
    public void when_PatchIsCalledOnActivatePhoneNumberPathWithValidNumber_thenReturnActivatedPhoneNumber() {
        ResponseEntity<PhoneNumber> response = restTemplate.exchange(
                createURLWithPort("/api/phonenumbers/+44-20-12345678/activate"),
                HttpMethod.PATCH,
                new HttpEntity<>(httpHeaders),
                PhoneNumber.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        PhoneNumber phoneNumber = response.getBody();
        assertNotNull(phoneNumber);
        assertTrue(phoneNumber.isActivated());
    }

    @Test
    public void when_PatchIsCalledOnActivatePhoneNumberPathWithInValidNumber_thenReturnNotFound() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/phonenumbers/123/activate"),
                HttpMethod.PATCH,
                new HttpEntity<>(httpHeaders),
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String createURLWithPort(String path) {
        return "http://localhost:" + port + path;
    }

}
