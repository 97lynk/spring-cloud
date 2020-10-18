package io.a97lynk.base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@EnableWebMvc
public class ExceptionCatcher extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataException.class)
    public ErrorInfo handleDataException(DataException ex) {
        return ErrorInfo.builder()
                .code("error.data")
                .message(ex.getMessage())
                .build();
    }


}
