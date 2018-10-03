package digital.and.telecomprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneNumberController {

    @GetMapping("/")
    String getGreeting() {
        return "Hello World! Welcome to the Telecom Provider REST API.";
    }

}
