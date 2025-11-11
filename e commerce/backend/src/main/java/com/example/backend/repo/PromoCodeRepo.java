package com.example.backend.repo;

import com.example.backend.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodeRepo extends JpaRepository<PromoCode, Long> {
    Optional<PromoCode> findByCodeIgnoreCase(String code);
}
