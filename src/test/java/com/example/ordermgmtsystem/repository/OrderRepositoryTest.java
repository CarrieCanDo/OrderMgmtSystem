package com.example.ordermgmtsystem.repository;

import com.example.ordermgmtsystem.entity.Order;
import com.example.ordermgmtsystem.entity.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        // Set up an Order object for testing
        order = new Order();
        order.setCustomerName("Test Customer");
        order.setOrderDate(LocalDateTime.now());
        order.setQuantity(3);
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(45.00);
    }

    @Test
    void testSaveOrder() {
        // Save an order
        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getCustomerName()).isEqualTo("Test Customer");
    }

    @Test
    void testFindAllOrders() {
        // Save an order
        orderRepository.save(order);

        // Retrieve all orders
        List<Order> orders = orderRepository.findAll();

        assertThat(orders).isNotEmpty();
        assertThat(orders.get(0).getCustomerName()).isEqualTo("Test Customer");
    }

    @Test
    void testDeleteOrder() {
        // Save an order
        Order savedOrder = orderRepository.save(order);

        // Delete the order
        orderRepository.delete(savedOrder);

        // Check if the order is deleted
        assertThat(orderRepository.findById(savedOrder.getId())).isEmpty();
    }
}
