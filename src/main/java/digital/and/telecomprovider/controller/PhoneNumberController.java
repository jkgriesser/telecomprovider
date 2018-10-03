package digital.and.telecomprovider.controller;

import digital.and.telecomprovider.exception.CustomerNotFoundException;
import digital.and.telecomprovider.exception.PhoneNumberNotFoundException;
import digital.and.telecomprovider.model.PhoneNumber;
import digital.and.telecomprovider.repository.PhoneNumberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneNumberController {

    private PhoneNumberRepository repository = new PhoneNumberRepository();

    @GetMapping("/")
    String getGreeting() {
        return "Hello World! Welcome to the Telecom Provider Phone Number REST API.";
    }

    @GetMapping("/phonenumbers")
    List<PhoneNumber> getAll() {
        return repository.getAll();
    }

    @GetMapping("/phonenumbers/")
    List<PhoneNumber> findByCustomerId(@RequestParam(value="customerId") Integer customerId) {
        return repository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @PatchMapping("phonenumbers/{phoneNumber}/activate")
    PhoneNumber activate(@PathVariable String phoneNumber) {
        return repository.activate(phoneNumber)
                .orElseThrow(() -> new PhoneNumberNotFoundException(phoneNumber));
    }

}
