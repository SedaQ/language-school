package com.fi.ls.rest.controller;

import com.fi.ls.rest.ApiError;
import com.fi.ls.rest.exception.ResourceAlreadyExistingException;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO
@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	ApiError handleException(ResourceAlreadyExistingException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("the requested resource already exists"));
		return apiError;
	}
}
