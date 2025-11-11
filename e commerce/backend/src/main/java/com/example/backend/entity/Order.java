package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> products = new ArrayList<>();

    @Column
    private LocalDateTime orderDate;

    @Column
    private LocalDateTime deliveryDate;

    @Column
    private Integer totalAmount;

    @Column
    private String status;

    @Embedded
    private Address address;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "quantity")
        private Integer quantity;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String phoneNumber;
        private String streetAddress;
        private String city;
        private String state;
        private String pincode;
    }
}