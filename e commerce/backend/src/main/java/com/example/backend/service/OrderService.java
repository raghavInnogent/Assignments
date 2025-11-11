package com.example.backend.service;

import com.example.backend.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
}
