package org.sterl.education.angular;

import java.beans.Introspector;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import lombok.Getter;
import lombok.ToString;

// see also https://sterl.org/2020/02/spring-boot-hateoas-jsr303-validation/
@ControllerAdvice
public class SpringExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody Map<String, Object> handleConstraintViolation(ConstraintViolationException e, ServletWebRequest request) {
        // emulate Spring DefaultErrorAttributes
        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("path", request.getRequest().getRequestURI());
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.put("message", e.getMessage());
        result.put("errors", e.getConstraintViolations().stream().map(cv -> SimpleObjectError.from(cv, messageSource, request.getLocale())));
        return result;
    }
    
    @Getter @ToString
    static class SimpleObjectError {
        String defaultMessage;
        String objectName;
        String field;
        Object rejectedValue;
        String code;
        
        public static SimpleObjectError from(ConstraintViolation<?> violation, MessageSource msgSrc, Locale locale) {
            SimpleObjectError result = new SimpleObjectError();
            result.defaultMessage = msgSrc.getMessage(violation.getMessageTemplate(),
                    new Object[] { violation.getLeafBean().getClass().getSimpleName(), violation.getPropertyPath().toString(),
                            violation.getInvalidValue() }, violation.getMessage(), locale);
            result.objectName = Introspector.decapitalize(violation.getRootBean().getClass().getSimpleName());
            result.field = String.valueOf(violation.getPropertyPath());
            result.rejectedValue = violation.getInvalidValue();
            result.code = violation.getMessageTemplate();
            return result;
        }
    }
}
