package com.autoflex.product_stock.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductMaterialDTO(
        Long id,
        @NotBlank(message = "O código é obrigatório")
        String codeProduct,

        @NotBlank(message = "O código é obrigatório")
        String codeMaterial,

        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade não pode ser negativa")
        Integer necessaryQuantity
) {}
