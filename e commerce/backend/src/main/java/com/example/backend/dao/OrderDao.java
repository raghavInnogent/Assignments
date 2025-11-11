package com.example.backend.dao;

import com.example.backend.entity.Order;
import com.example.backend.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {

    @Autowired
    private OrderRepo orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}