package ro.deloittedigital.bootcampbackend.boundry.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
