package com.innogent.E_Commerce.serviceImpl;

import com.innogent.E_Commerce.dao.ProductDao;
import com.innogent.E_Commerce.dto.ProductResponseDto;
import com.innogent.E_Commerce.entity.Product;
import com.innogent.E_Commerce.mapper.ProductMapper;
import com.innogent.E_Commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> products = productDao.findAllProducts();
        return products.stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }


}
