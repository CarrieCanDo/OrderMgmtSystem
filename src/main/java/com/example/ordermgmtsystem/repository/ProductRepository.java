package com.example.ordermgmtsystem.repository;

import com.example.ordermgmtsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods (if needed) can go here
}
