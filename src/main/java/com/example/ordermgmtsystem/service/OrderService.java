package com.example.ordermgmtsystem.service;

import com.example.ordermgmtsystem.entity.Order;
import com.example.ordermgmtsystem.entity.Product;
import com.example.ordermgmtsystem.entity.enums.OrderStatus;
import com.example.ordermgmtsystem.repository.OrderRepository;
import com.example.ordermgmtsystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // Constructor injection
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order, List<Long> productIds) {
        // Fetch products by their IDs and set them to the order
        List<Product> products = productRepository.findAllById(productIds);
        order.setProducts(products);

        order.setStatus(OrderStatus.NEW);  // Set the initial status as NEW
        order.setTotalPrice(order.recalculateTotalPrice());  // Calculate the total price
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order updatedOrder, List<Long> productIds) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);

        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            existingOrder.setCustomerName(updatedOrder.getCustomerName());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setQuantity(updatedOrder.getQuantity());

            // Update the product list and recalculate the total price
            List<Product> products = productRepository.findAllById(productIds);
            existingOrder.setProducts(products);

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
