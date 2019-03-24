/**
 * 
 */
package com.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exception.ValidationException;
import com.common.ResponseUtil;
import com.common.ValidationErrorMessage;

/**
 * @author virens
 *
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ValidationException ex, WebRequest request) {
		ResponseUtil<List<ValidationErrorMessage>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(ex.getViolations());
		responseUtil.setMessage("Validation Failed.");
		return new ResponseEntity<>(responseUtil, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult bindingResult = ex.getBindingResult();
		List<ValidationErrorMessage> errorList = new ArrayList<>();

		bindingResult.getFieldErrors().forEach(fieldError -> {
			ValidationErrorMessage errorDetailObj = new ValidationErrorMessage();
			errorDetailObj.setField(fieldError.getField());
			errorDetailObj.setInValidValue(fieldError.getRejectedValue());
			errorDetailObj.setErrorMessage(fieldError.getDefaultMessage());
			errorDetailObj.setErrorMessageKey(fieldError.getCode());
			errorList.add(errorDetailObj);
		});

		ResponseUtil<List<ValidationErrorMessage>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(errorList);
		responseUtil.setMessage("Validation Failed.");
		return new ResponseEntity<>(responseUtil, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ResponseUtil<List<ValidationErrorMessage>> responseUtil = new ResponseUtil<>();
		ValidationErrorMessage errorDetailObj = new ValidationErrorMessage();
		errorDetailObj.setErrorMessage(ex.getLocalizedMessage());
		responseUtil.setData(Collections.singletonList(errorDetailObj));
		responseUtil.setMessage("Something went wrong.");
		return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
