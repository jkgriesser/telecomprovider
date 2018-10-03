package digital.and.telecomprovider.exception;

public class PhoneNumberNotFoundException extends RuntimeException {

    public PhoneNumberNotFoundException(String phoneNumber) {
        super("Could not find phone number: " + phoneNumber);
    }

}
