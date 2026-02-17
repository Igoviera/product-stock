package com.autoflex.product_stock.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        @NotBlank(message = "O código é obrigatório")
        @Size(min = 4, max = 10, message = "O código deve ter entre 4 e 10 caracteres")
        String code,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 20 caracteres")
        String name,

        @NotNull(message = "O valor é obrigatório")
        @PositiveOrZero(message = "O valor não pode ser negativo")
        BigDecimal price
) {}
