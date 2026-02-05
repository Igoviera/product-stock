package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.ProductDTO;
import com.autoflex.product_stock.dtos.ProductionSuggestionDTO;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.repository.ProductRepository;
import com.autoflex.product_stock.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public void create(@RequestBody Product product){
        productService.create(product);
    }

    @GetMapping()
    public Set<ProductDTO> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable("productId") Long productId){
        return productService.getById(productId);
    }

    @GetMapping("/suggest")
    public ProductionSuggestionDTO suggestProduction(){
        return productService.suggestProduction();
    }
}
