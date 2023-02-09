package com.ey.demo.product.service;

import java.util.List;

import com.ey.demo.product.entity.Product;


public interface ProductService {
	
	public List<Product> getAllProducts();

	public void deleteProductById(Long id);

	public void saveProduct(Product product);

	

}
