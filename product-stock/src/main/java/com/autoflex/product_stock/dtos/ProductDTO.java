package com.autoflex.product_stock.dtos;

import com.autoflex.product_stock.model.ProductMaterial;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

import java.util.Set;

public record ProductDTO(

        @NotBlank(message = "Codigo obrigatório")
        String code,

        @NotBlank(message = "Nome obrigatório")
        String name,

        @NotBlank(message = "Codigo obrigatório")
        BigDecimal price,

        Set<ProductMaterial> materials
) {}
