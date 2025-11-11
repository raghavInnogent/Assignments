package com.example.backend.controller;

import com.example.backend.dto.ProductDto;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts()
    {
        List<ProductDto> productList = productService.getAllProducts();
        if(productList.isEmpty() || productList == null)
        {
            return new ResponseEntity<List<ProductDto>>(productList, HttpStatus.BAD_REQUEST);
        }
        else 
        {
        	return new ResponseEntity<List<ProductDto>>(productList, HttpStatus.OK);
        }
    }

}
