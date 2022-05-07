package com.masai.Spring_Projects.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Spring_Projects.Assignment_Day_1.Beans.Product;
import com.masai.Spring_Projects.custom_exceptions.InvalidInputException;
import com.masai.Spring_Projects.custom_exceptions.NonUniqueIdException;
import com.masai.Spring_Projects.custom_exceptions.productNotFoundException;

@RestController
public class controller {
	
	private static List<Product> listOfAllProducts = new ArrayList<>();
	
	@GetMapping("/products")
	public List<Product> products() {
		return listOfAllProducts;
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		
		if(id < 0) {
			throw new InvalidInputException("ID is invalid. Please confirm");
		}
		
		for(Product product: listOfAllProducts) {
			if(product.getId() == id) {
				return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
			}
		}
		throw new productNotFoundException("No such product");
	}
	
	@PostMapping("/product")
	public String addProduct(@RequestBody Product product) {
		//Validating the id
		for(int i = 0; i < listOfAllProducts.size(); i++) {
			if(listOfAllProducts.get(i).getId() == product.getId()) {
				throw new NonUniqueIdException("Non Unique ID");
			}
		}
		
		//Validating the name
		if(product.getName() == null || product.getName() == "") {
			throw new InvalidInputException("Proper product name is required!");
		}
		
		//Validating the Brand name
		if(product.getBrand() == null || product.getBrand() == "") {
			throw new InvalidInputException("Proper Brand name is required!");
		}
		
		//Validating the Category
		if(product.getCategory() == null || product.getCategory() == "") {
			throw new InvalidInputException("Proper Category is required!");
		}
		
		//Validating the registration number
		if(!Pattern.matches("[0-9]{6}", String.valueOf(product.getRegistration_number()))) {
			throw new InvalidInputException("Registration number needs to have exactly 6 digits!");
		}
		
		listOfAllProducts.add(product);
		return "Product successfully added";
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable Integer id) {
		
		for(int i = 0; i < listOfAllProducts.size(); i++) {
			if(listOfAllProducts.get(i).getId() == id) {
				listOfAllProducts.remove(i);
				return new ResponseEntity(id, HttpStatus.ACCEPTED);
			}
		}
		
		throw new productNotFoundException("No such product");
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		for(int i = 0; i < listOfAllProducts.size(); i++) {
			if(listOfAllProducts.get(i).getId() == id) {
				listOfAllProducts.set(i, product);
				return new ResponseEntity(id, HttpStatus.ACCEPTED);
			}
		}
		
		throw new productNotFoundException("No such product");
	}
}
