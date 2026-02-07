package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.ProductDTO;
import com.autoflex.product_stock.dtos.ProductionSuggestionDTO;
import com.autoflex.product_stock.repository.ProductRepository;
import com.autoflex.product_stock.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @GetMapping()
    public Set<ProductDTO> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public ProductDTO getById(@Valid @PathVariable("productId") Long productId){
        return productService.getById(productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@Valid @PathVariable("productId") Long productId){
        productService.delete(productId);
    }

    @GetMapping("/suggest")
    public ProductionSuggestionDTO suggestProduction(){
        return productService.suggestProduction();
    }
}
