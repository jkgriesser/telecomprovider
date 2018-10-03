package digital.and.telecomprovider.repository;

import digital.and.telecomprovider.model.Customer;
import digital.and.telecomprovider.model.PhoneNumber;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Placeholder repository for the phone number API
 */
@Repository
public class PhoneNumberRepository {

    private Map<String, PhoneNumber> phoneNumbers = new HashMap<>();
    private Map<Integer, Customer> customersById = new HashMap<>();
    private Map<Customer, List<PhoneNumber>> customerPhoneNumberMap = new HashMap<>();

    public PhoneNumberRepository() {
        seedTestValues();
    }

    public List<PhoneNumber> getAll() {
        return new ArrayList<>(phoneNumbers.values());
    }

    public Optional<List<PhoneNumber>> findByCustomerId(Integer customerId) {
        Assert.notNull(customerId, "The given id must not be null!");

        Customer customer = customersById.get(customerId);
        return Optional.ofNullable(customer == null
                ? null
                : customerPhoneNumberMap.get(customer));
    }

    public Optional<PhoneNumber> activate(String phoneNumberString) {
        Assert.notNull(phoneNumberString, "The given phone number must not be null!");

        PhoneNumber phoneNumber = phoneNumbers.get(phoneNumberString);
        if (phoneNumber != null) {
            phoneNumber.setActivated(true);
        }
        return Optional.ofNullable(phoneNumber);
    }

    /**
     * Seeds the repository with test values
     */
    private void seedTestValues() {
        List<PhoneNumber> alicesPhoneNumbers = Arrays.asList(
                new PhoneNumber("+44-20-12345678"),
                new PhoneNumber("+44-20-55555555"));
        List<PhoneNumber> bobsPhoneNumbers = Arrays.asList(
                new PhoneNumber("+44-20-87654321"),
                new PhoneNumber("+44-20-66666666"),
                new PhoneNumber("+44-20-77777777"));
        alicesPhoneNumbers.forEach((pn) -> phoneNumbers.put(pn.toString(), pn));
        bobsPhoneNumbers.forEach((pn) -> phoneNumbers.put(pn.toString(), pn));

        Customer alice = new Customer(1, "Alice");
        Customer bob = new Customer(2, "Bob");
        Customer eve = new Customer(3, "Eve");
        customersById.put(1, alice);
        customersById.put(2, bob);
        customersById.put(3, eve);

        customerPhoneNumberMap.put(
                alice,
                alicesPhoneNumbers);
        customerPhoneNumberMap.put(
                bob,
                bobsPhoneNumbers);
        customerPhoneNumberMap.put(
                eve,
                new ArrayList<>());
    }

}
