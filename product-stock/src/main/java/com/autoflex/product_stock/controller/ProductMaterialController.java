package com.autoflex.product_stock.controller;


import com.autoflex.product_stock.dtos.ProductMaterialDTO;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.service.ProductMaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/compositions")
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;

    public ProductMaterialController(ProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;
    }

    @PostMapping
    public ProductMaterialDTO create(@RequestBody ProductMaterialDTO productMaterialDTO){
       return productMaterialService.create(productMaterialDTO);
    }
}
