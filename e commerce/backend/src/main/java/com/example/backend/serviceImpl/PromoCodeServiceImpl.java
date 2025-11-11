package com.example.backend.serviceImpl;

import com.example.backend.dao.PromoCodeDao;
import com.example.backend.entity.PromoCode;
import com.example.backend.repo.PromoCodeRepo;
import com.example.backend.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {

    @Autowired
    private PromoCodeRepo promoCodeRepo;

    @Override
    public Optional<PromoCode> validatePromoCode(String code) {
        return promoCodeRepo.findByCodeIgnoreCase(code);
    }
}
