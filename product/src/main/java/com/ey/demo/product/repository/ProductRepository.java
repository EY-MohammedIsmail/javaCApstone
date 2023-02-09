package com.ey.demo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.demo.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
