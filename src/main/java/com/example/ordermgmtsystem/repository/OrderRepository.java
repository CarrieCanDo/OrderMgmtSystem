package com.example.ordermgmtsystem.repository;

import com.example.ordermgmtsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods (if needed) can go here
}
