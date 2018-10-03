package digital.and.telecomprovider.advice;

import digital.and.telecomprovider.exception.CustomerNotFoundException;
import digital.and.telecomprovider.exception.PhoneNumberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PhoneNumberControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String languageNotFoundHandler(CustomerNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PhoneNumberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String languageNotFoundHandler(PhoneNumberNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidParameterHandler(IllegalArgumentException ex) {
        return ex.getMessage();
    }

}
