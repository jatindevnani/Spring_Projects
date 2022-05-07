package com.masai.Spring_Projects.custom_exceptions;

import org.springframework.stereotype.Component;
import java.lang.String;


public class NonUniqueIdException extends RuntimeException{

	public NonUniqueIdException(String message) {
		super(message);
	}
}
