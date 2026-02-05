package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.ProductionSuggestionDTO;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.repository.MaterialRepository;
import com.autoflex.product_stock.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;

    public ProductService(ProductRepository productRepository, MaterialRepository materialRepository) {
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
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

    public ProductionSuggestionDTO suggestProduction(){
       List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC));


        for (Product product : products) {
            System.out.printf("Produtos " + product);
        }

       List<Material> materials = materialRepository.findAll();

       return null;

    }
}
