package com.example.backend.service;

import com.example.backend.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDto> getAllProducts();

}
