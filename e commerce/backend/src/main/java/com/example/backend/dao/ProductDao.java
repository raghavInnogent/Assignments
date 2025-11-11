package com.example.backend.dao;

import com.example.backend.dto.ProductDto;
import com.example.backend.entity.Product;
import com.example.backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {
    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts()
    {
        return productRepo.findAll();
    }
}
