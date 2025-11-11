package com.example.backend.controller;

import com.example.backend.entity.PromoCode;
import com.example.backend.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PromoCodeController {

    @Autowired
    private PromoCodeService promoCodeService;

    @GetMapping("/validate/{code}")
    public ResponseEntity<?> validatePromoCode(@PathVariable String code) {
        return promoCodeService.validatePromoCode(code)
                .map(promoCode -> {
                    Map<String, Object> body = new HashMap<>();
                    body.put("success", true);
                    body.put("code", promoCode.getCode());
                    body.put("discount", promoCode.getDiscount());
                    return ResponseEntity.ok(body);
                })
                .orElseGet(() -> {
                    Map<String, Object> body = new HashMap<>();
                    body.put("success", false);
                    body.put("message", "No promo code found with name: " + code);
                    return ResponseEntity.status(404).body(body);
                });
    }
}
