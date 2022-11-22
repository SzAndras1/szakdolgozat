package hu.cowork.advertising;

import hu.cowork.advertising.model.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        ErrorDto apiError = new ErrorDto();
        apiError.setCode(500);
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, INTERNAL_SERVER_ERROR);
    }

}
