package com.example.backend.serviceImpl;

import com.example.backend.dao.OrderDao;
import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Order;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order.setOrderDate(LocalDateTime.now());
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }
        Order savedOrder = orderDao.save(order);
        return orderMapper.toDto(savedOrder);
    }

    public List<OrderDto> getAllOrders() {
        return orderDao.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}