package com.masai.Spring_Projects.custom_exceptions;

import org.springframework.stereotype.Component;
import java.lang.String;


public class productNotFoundException extends RuntimeException{

	public productNotFoundException(String message) {
		super(message);
	}
}
