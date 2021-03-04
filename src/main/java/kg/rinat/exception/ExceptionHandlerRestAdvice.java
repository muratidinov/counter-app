package kg.rinat.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerRestAdvice {

    @ExceptionHandler(CounterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleCounterException(CounterException exception) {
        log.error(exception.getLocalizedMessage());
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception exception) {
        log.error(exception.getLocalizedMessage());
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
    }
}
