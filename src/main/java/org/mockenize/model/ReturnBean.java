package org.mockenize.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

public class ReturnBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	@JsonInclude(Include.NON_NULL)
	private Class<?> exceptionClass;

	public ReturnBean(Throwable throwable) {
		message = throwable.getMessage();
		exceptionClass = throwable.getClass();
	}

	public ReturnBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class<?> getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(Class<?> exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

}
