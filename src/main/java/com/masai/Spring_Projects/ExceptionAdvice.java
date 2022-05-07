package com.masai.Spring_Projects;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.masai.Spring_Projects.custom_exceptions.InvalidInputException;
import com.masai.Spring_Projects.custom_exceptions.productNotFoundException;
import com.masai.Spring_Projects.custom_exceptions.NonUniqueIdException;

@ControllerAdvice
public class ExceptionAdvice {
	

	@ExceptionHandler(productNotFoundException.class)
	public ResponseEntity<String> handleNoProductFound(productNotFoundException noProduct) {
		return new ResponseEntity<String>(noProduct.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NonUniqueIdException.class)
	public ResponseEntity<String> handleNonUniqueId(NonUniqueIdException noProduct) {
		return new ResponseEntity<String>("Product with this ID already exists!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidInputException.class) 
	public ResponseEntity<String> handleInvalidInput(InvalidInputException invalidID) {
		return new ResponseEntity<String>(invalidID.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> noHandler(NoHandlerFoundException noHandler) {
		return new ResponseEntity<String>("Invalid URL, Check again.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MethodNotAllowedException.class)
	public ResponseEntity<HashMap<String, String>> handleMethodNotAllowed(MethodNotAllowedException notAllowed) {
		Map<String, String> response = new HashMap();
		response.put("Log", "Method not allowed for this url");
		response.put("Error_Code", "405");
		return new ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
