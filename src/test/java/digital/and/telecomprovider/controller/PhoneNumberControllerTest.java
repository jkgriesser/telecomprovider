package digital.and.telecomprovider.controller;

import digital.and.telecomprovider.exception.CustomerNotFoundException;
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

    @Test
    public void when_findByCustomerIdCalledWithValidId_thenReturnListOfPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = controller.findByCustomerId(1);

        assertFalse(phoneNumbers.isEmpty());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void when_findByCustomerIdCalledWithInvalidId_thenThrowException() {
        controller.findByCustomerId(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_findByCustomerIdCalledWithNull_thenThrowException() {
        controller.findByCustomerId(null);
    }

}
