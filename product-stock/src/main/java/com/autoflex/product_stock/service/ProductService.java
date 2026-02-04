package com.autoflex.product_stock.service;

import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product){
        productRepository.save(product);
    }

    public Product getById(Long productId) {
       return productRepository.findById(productId)
               .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
