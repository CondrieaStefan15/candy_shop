package ro.deloittedigital.bootcampbackend.boundry.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.deloittedigital.bootcampbackend.boundry.dto.ErrorDTO;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.AlreadyExistsException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.BadRequestException;
import ro.deloittedigital.bootcampbackend.boundry.exceptions.NotFoundException;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class WebRestControllerAdvice {

    private ErrorDTO generateError(HttpStatus httpStatus, Exception exception) {
        return new ErrorDTO(httpStatus.value(), exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDTO handleAlreadyExists(AlreadyExistsException exception) {
        return generateError(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundException ex) {
        return generateError(HttpStatus.NOT_FOUND, ex);
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequestException(BadRequestException ex) {
        return generateError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException ex) {
        return generateError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return generateError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO defaultHandler(Exception ex) {
        return this.generateError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }


}
