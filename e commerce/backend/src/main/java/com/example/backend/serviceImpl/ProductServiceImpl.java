package com.example.backend.serviceImpl;

import com.example.backend.dao.ProductDao;
import com.example.backend.dto.ProductDto;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductDto> getAllProducts() {
        return productDao.getAllProducts().stream().map((product)->new ProductDto(product.getId(),
                                                                                    product.getTitle(),
                                                                                    product.getDescription(),
                                                                                    product.getPrice(),
                                                                                    product.getCategory(),
                                                                                    product.getImage(),
                                                                                    product.getRatingRate(),
                                                                                    product.getRatingCount())).toList();

    }
}
