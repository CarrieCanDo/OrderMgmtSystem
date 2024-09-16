package com.example.ordermgmtsystem.service;

import com.example.ordermgmtsystem.entity.Order;
import com.example.ordermgmtsystem.entity.Product;
import com.example.ordermgmtsystem.entity.enums.OrderStatus;
import com.example.ordermgmtsystem.repository.OrderRepository;
import com.example.ordermgmtsystem.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    private Order order;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // No try-with-resources needed here

        order = new Order();
        order.setId(1L);
        order.setCustomerName("John Doe");
        order.setOrderDate(LocalDateTime.now());
        order.setQuantity(2);

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(10.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(15.0);
    }

    @Test
    void testCreateOrder() {
        List<Long> productIds = Arrays.asList(1L, 2L);
        when(productRepository.findAllById(productIds)).thenReturn(Arrays.asList(product1, product2));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order, productIds);

        assertNotNull(createdOrder);
        assertEquals(25.0, createdOrder.getTotalPrice());  // 10.0 + 15.0 = 25.0
        assertEquals(OrderStatus.NEW, createdOrder.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testUpdateOrder() {
        List<Long> productIds = Collections.singletonList(1L);  // Use Collections.singletonList() instead of Arrays.asList()
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(productRepository.findAllById(productIds)).thenReturn(Collections.singletonList(product1));  // Use Collections.singletonList()
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        order.setQuantity(5);
        order.setCustomerName("Jane Doe");
        Order updatedOrder = orderService.updateOrder(1L, order, productIds);

        assertNotNull(updatedOrder);
        assertEquals("Jane Doe", updatedOrder.getCustomerName());
        assertEquals(10.0, updatedOrder.getTotalPrice());  // Only product 1
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));  // Use Collections.singletonList()

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAll();
    }
}
