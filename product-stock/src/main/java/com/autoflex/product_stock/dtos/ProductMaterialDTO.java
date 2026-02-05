package com.autoflex.product_stock.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductMaterialDTO(
        @NotBlank(message = "O código é obrigatório")
        String codeProduct,

        @NotBlank(message = "O código é obrigatório")
        String codeMaterial,

        @NotNull(message = "A quantidade é obrigatória")
        Integer necessaryQuantity
) {}
