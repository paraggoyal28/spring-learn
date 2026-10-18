package com.learn.spring.basic_hiberante_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFoundException(Throwable cause, String resourceName, String fieldName,
            Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue), cause);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getResourceName() {
        return resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public Object getFieldValue() {
        return fieldValue;
    }
    
    
}
