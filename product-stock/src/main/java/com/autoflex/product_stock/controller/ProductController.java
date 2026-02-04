package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void create(@RequestBody Product product){
        productService.create(product);
    }

    @GetMapping()
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable("productId") Long productId){
        return productService.getById(productId);
    }
}
