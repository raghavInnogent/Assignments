package com.example.backend.controller;

import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Order;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {
            OrderDto responseDto = orderService.createOrder(orderDto);
            return ResponseEntity.ok(responseDto);

        } catch (Exception e) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("success", false);
            errorBody.put("message", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorBody);
        }
    }


    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}