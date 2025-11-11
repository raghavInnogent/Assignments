package com.example.backend.mapper;

import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Order;
import com.example.backend.entity.Product;
import com.example.backend.entity.PromoCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());

        if (order.getAddress() != null) {
            OrderDto.AddressDto addressDto = new OrderDto.AddressDto(
                    order.getAddress().getPhoneNumber(),
                    order.getAddress().getStreetAddress(),
                    order.getAddress().getCity(),
                    order.getAddress().getState(),
                    order.getAddress().getPincode()
            );
            dto.setAddress(addressDto);
        }

        if (order.getProducts() != null) {
            List<OrderDto.OrderItemDto> productDtoList = order.getProducts()
                    .stream()
                    .map(orderItem -> new OrderDto.OrderItemDto(
                            toProductDto(orderItem.getProduct()),
                            orderItem.getQuantity()
                    ))
                    .collect(Collectors.toList());
            dto.setProducts(productDtoList);
        }



        return dto;
    }

    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        order.setId(dto.getId());
        order.setOrderDate(dto.getOrderDate());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());

        if (dto.getAddress() != null) {
            Order.Address address = new Order.Address(
                    dto.getAddress().getPhoneNumber(),
                    dto.getAddress().getStreetAddress(),
                    dto.getAddress().getCity(),
                    dto.getAddress().getState(),
                    dto.getAddress().getPincode()
            );
            order.setAddress(address);
        }

        if (dto.getProducts() != null) {
            List<Order.OrderItem> orderItems = dto.getProducts()
                    .stream()
                    .map(itemDto -> new Order.OrderItem(
                            toProductEntity(itemDto.getProduct()),
                            itemDto.getQuantity()
                    ))
                    .collect(Collectors.toList());
            order.setProducts(orderItems);
        }

        return order;
    }

    private OrderDto.ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        return new OrderDto.ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImage(),
                product.getRatingRate(),
                product.getRatingCount()
        );
    }

    private Product toProductEntity(OrderDto.ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setImage(productDto.getImage());
        product.setRatingRate(productDto.getRatingRate());
        product.setRatingCount(productDto.getRatingCount());
        return product;
    }
}