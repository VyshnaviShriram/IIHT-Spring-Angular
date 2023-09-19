package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionHandler extends RuntimeException{
	
	private static final long serialVersionUID = 1l;
	private String ResourceName;
	private String fieldName;
	private Object fieldValue;
	
	public String getResourceName() {
		return ResourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public ResourceNotFoundExceptionHandler(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s with %s : %s is not found",resourceName,  fieldName, fieldValue ));
		ResourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	

}
