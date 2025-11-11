package com.example.backend.service;

import com.example.backend.entity.PromoCode;

import java.util.Optional;

public interface PromoCodeService {
    Optional<PromoCode> validatePromoCode(String code);
}
