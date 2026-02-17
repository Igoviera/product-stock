package com.autoflex.product_stock.dtos.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record MaterialDTO (
        Long id,
        @NotBlank(message = "O código é obrigatório")
        @Size(min = 4, max = 10, message = "O código deve ter entre 4 e 10 caracteres")
        String code,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 20, message = "O nome deve ter entre 2 e 20 caracteres")
        String name,

        @NotNull(message = "A quantidade é obrigatório")
        @PositiveOrZero(message = "A quantidade não pode ser negativo")
        Integer stockQuantity
){
}
