package com.autoflex.product_stock.repository;

import com.autoflex.product_stock.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByCode(String code);
}
