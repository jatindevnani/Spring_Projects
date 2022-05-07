package com.masai.Spring_Projects.custom_exceptions;

import org.springframework.stereotype.Component;
import java.lang.String;


public class InvalidInputException extends RuntimeException{

	public InvalidInputException(String message) {
		super(message);
	}
}
