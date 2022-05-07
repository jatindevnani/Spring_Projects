package com.masai.Spring_Projects.controllers;

import java.util.ArrayList;
import java.util.List;

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

@RestController
public class controller {
	
	private static List<Product> listOfAllProducts = new ArrayList<>();
	
	@GetMapping("/products")
	public List<Product> products() {
		return listOfAllProducts;
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id) {
		for(Product product: listOfAllProducts) {
			if(product.getId() == id) {
				return product;
			}
		}
		return new Product();	
	}
	
	@PostMapping("/product")
	public String addProduct(@RequestBody Product product) {
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
		
		return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		for(int i = 0; i < listOfAllProducts.size(); i++) {
			if(listOfAllProducts.get(i).getId() == id) {
				listOfAllProducts.set(i, product);
				return new ResponseEntity(id, HttpStatus.ACCEPTED);
			}
		}
		return new ResponseEntity(new Product(), HttpStatus.BAD_REQUEST);
	}

	
}
