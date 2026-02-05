package com.autoflex.product_stock.repository;

import com.autoflex.product_stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
