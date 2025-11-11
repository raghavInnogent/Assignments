package com.innogent.E_Commerce.serviceImpl;

import com.innogent.E_Commerce.dao.OrderDao;
import com.innogent.E_Commerce.dto.OrderRequestDto;
import com.innogent.E_Commerce.dto.OrderResponseDto;
import com.innogent.E_Commerce.entity.Order;
import com.innogent.E_Commerce.mapper.OrderMapper;
import com.innogent.E_Commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toEntity(orderRequestDto);
        Order savedOrder = orderDao.saveOrder(order);
        
        savedOrder.updateStatus();
        savedOrder = orderDao.saveOrder(savedOrder);
        return orderMapper.toResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> findAllOrders() {
        List<Order> orders = orderDao.findAllOrders();

        orders.forEach(order -> {
            order.updateStatus();
            orderDao.saveOrder(order);
        });
        
        return orders.stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }


}

