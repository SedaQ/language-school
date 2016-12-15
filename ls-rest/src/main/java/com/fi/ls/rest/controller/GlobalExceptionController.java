package com.fi.ls.rest.controller;

import com.fi.ls.rest.ApiError;
import com.fi.ls.rest.exception.*;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@ControllerAdvice
public class GlobalExceptionController {
        
        @ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST) //400
	@ResponseBody
	ApiError handleException(BadRequestException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("Bad request to resource"));
		return apiError;
	}
        
        @ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE) //406
	@ResponseBody
	ApiError handleException(InvalidParameterException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("Invaild request parameters"));
		return apiError;
	}
        
        @ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //422
	@ResponseBody
	ApiError handleException(ResourceAlreadyExistingException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("The requested resource already exists"));
		return apiError;
	}
        
        @ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND) //404
	@ResponseBody
	ApiError handleException(ResourceNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("The requested resource was not found"));
		return apiError;
	}
        
        @ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_MODIFIED) //304
	@ResponseBody
	ApiError handleException(ResourceNotModifiedException ex) {
		ApiError apiError = new ApiError();
		apiError.setErrors(Arrays.asList("The requested resource was not modified"));
		return apiError;
	}
}
