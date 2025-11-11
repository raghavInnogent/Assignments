package com.innogent.E_Commerce.service;

import com.innogent.E_Commerce.dto.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDto> findAll();

}
