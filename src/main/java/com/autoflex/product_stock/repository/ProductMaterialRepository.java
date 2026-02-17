package com.autoflex.product_stock.repository;

import com.autoflex.product_stock.model.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {
}
