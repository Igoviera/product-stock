package com.autoflex.product_stock.repository;

import com.autoflex.product_stock.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Optional<Material> findByCode(String code);
}
