package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.MaterialDTO;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public MaterialDTO create(@Valid @RequestBody MaterialDTO materialDTO){
       return materialService.create(materialDTO);
    }
}
