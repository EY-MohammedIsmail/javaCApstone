package com.ey.demo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.demo.product.entity.Product;
import com.ey.demo.product.service.ProductService;



@RestController
@RequestMapping("/home")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/productList")
	public List<Product> getAll() {
//		model.addAttribute("product", productService.getAllProducts());
		return productService.getAllProducts();
	}
	@PostMapping("/addnew")
	public void addProduct(@RequestBody Product product){
	    productService.saveProduct(product);
	}
	@GetMapping("/showNewStudentForm")
	public String addProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "addProductForm";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("student")Product product){
		
		productService.saveProduct(product);
		return "redirect:/";
		
	}

	@DeleteMapping("/deleteProductById/{id}")
	public void deleteProductById(@PathVariable("id")Long id) {
		
		productService.deleteProductById(id);
	}
	

}
