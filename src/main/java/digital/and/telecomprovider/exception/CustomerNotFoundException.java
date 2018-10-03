package digital.and.telecomprovider.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer id) {
        super("Could not find customer with ID #" + id);
    }

}
