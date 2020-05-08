package org.sterl.education.angular;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

// see also https://sterl.org/2020/02/spring-boot-hateoas-jsr303-validation/
@ControllerAdvice
public class SpringExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleConstraintViolation(ConstraintViolationException e, ServletWebRequest request) {
        // emulate Spring DefaultErrorAttributes
        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("path", request.getRequest().getRequestURI());
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.put("message", e.getMessage());
        result.put("errors", e.getConstraintViolations());
        return result;
    }
}
