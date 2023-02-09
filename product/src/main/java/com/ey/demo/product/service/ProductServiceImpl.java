package com.ey.demo.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.demo.product.entity.Product;
import com.ey.demo.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
		
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
		
	}



}
