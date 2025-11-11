package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private List<OrderItemDto> products;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private Integer totalAmount;

    private String status;

    private AddressDto address;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDto {
        private ProductDto product;
        private Integer quantity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressDto {
        private String phoneNumber;
        private String streetAddress;
        private String city;
        private String state;
        private String pincode;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDto {
        private Long id;
        private String title;
        private String description;
        private Double price;
        private String category;
        private String image;
        private Double ratingRate;
        private Integer ratingCount;
    }


}