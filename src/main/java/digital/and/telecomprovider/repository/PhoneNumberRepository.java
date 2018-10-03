package digital.and.telecomprovider.repository;

import digital.and.telecomprovider.model.PhoneNumber;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Placeholder repository for the phone number API
 */
@Repository
public class PhoneNumberRepository {

    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    /**
     * Seeds the repository with test values
     */
    public PhoneNumberRepository() {
        phoneNumbers.addAll(Arrays.asList(
                new PhoneNumber("+44 20 12345678"),
                new PhoneNumber("+44 20 55555555"),
                new PhoneNumber("+44 20 87654321"),
                new PhoneNumber("+44 20 66666666"),
                new PhoneNumber("+44 20 77777777")));
    }

    public List<PhoneNumber> getAll() {
        return phoneNumbers;
    }

}
