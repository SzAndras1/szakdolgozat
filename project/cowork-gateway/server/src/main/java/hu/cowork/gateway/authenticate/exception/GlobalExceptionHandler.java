package hu.cowork.gateway.authenticate.exception;

import hu.cowork.cowork_gateway.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgument(Exception ex) {
        ErrorDto errorDto = new ErrorDto()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }
}
