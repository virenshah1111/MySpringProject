/**
 * 
 */
package com.common;

/**
 * @author virens
 *
 */
public class ValidationErrorMessage {

	private String field;
	private Object inValidValue;
	private String errorMessageKey;
	private String errorMessage;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getInValidValue() {
		return inValidValue;
	}

	public void setInValidValue(Object inValidValue) {
		this.inValidValue = inValidValue;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
