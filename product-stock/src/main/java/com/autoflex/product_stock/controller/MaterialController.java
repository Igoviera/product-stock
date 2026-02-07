package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.MaterialDTO;
import com.autoflex.product_stock.dtos.ProductDTO;
import com.autoflex.product_stock.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MaterialDTO create(@Valid @RequestBody MaterialDTO materialDTO){
       return materialService.create(materialDTO);
    }

    @GetMapping()
    public Set<MaterialDTO> getAll(){
        return materialService.getAll();
    }

    @GetMapping("/{materialId}")
    public MaterialDTO getById(@Valid @PathVariable("materialId") Long materialId){
        return materialService.getById(materialId);
    }
}
