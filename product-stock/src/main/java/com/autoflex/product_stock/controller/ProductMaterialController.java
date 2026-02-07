package com.autoflex.product_stock.controller;


import com.autoflex.product_stock.dtos.ProductMaterialDTO;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.repository.ProductRepository;
import com.autoflex.product_stock.service.ProductMaterialService;
import com.autoflex.product_stock.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/compositions")
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;


    public ProductMaterialController(ProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductMaterialDTO create(@RequestBody ProductMaterialDTO productMaterialDTO){
       return productMaterialService.create(productMaterialDTO);
    }

}
