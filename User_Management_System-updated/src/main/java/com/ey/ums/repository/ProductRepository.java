package com.ey.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.ums.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
