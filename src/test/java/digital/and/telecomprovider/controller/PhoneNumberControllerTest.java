package digital.and.telecomprovider.controller;

import digital.and.telecomprovider.model.PhoneNumber;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class PhoneNumberControllerTest {

    private PhoneNumberController controller;

    @Before
    public void init() {
        controller = new PhoneNumberController();
    }

    @Test
    public void when_getAllCalled_thenReturnListOfPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = controller.getAll();

        assertFalse(phoneNumbers.isEmpty());
    }

}
