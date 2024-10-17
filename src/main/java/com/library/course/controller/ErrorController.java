package com.library.course.controller;

import com.library.course.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

	@ExceptionHandler( Exception.class )
	public ResponseEntity< ErrorDTO > handleBadRequestException (Exception e){
		ErrorDTO errorDTO = ErrorDTO.builder().errorMessage(e.getMessage()).build();
		return new ResponseEntity <>(errorDTO, HttpStatus.I_AM_A_TEAPOT);
	}
}
