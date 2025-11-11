package com.innogent.E_Commerce.controller;

import com.innogent.E_Commerce.dto.OrderRequestDto;
import com.innogent.E_Commerce.dto.OrderResponseDto;
import com.innogent.E_Commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto order = orderService.createOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }



}

