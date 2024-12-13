package by.piatrouski.iss.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final DefaultErrorAttributes defaultErrorAttributes;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
                                                                 final WebRequest request) {

        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,
                HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
        final Map<String, Object> errorAttributes =
                defaultErrorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        errorAttributes.put("message", ex.getMessage());

        return handleExceptionInternal(ex, errorAttributes, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
