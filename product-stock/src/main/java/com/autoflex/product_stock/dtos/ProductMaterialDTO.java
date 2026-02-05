package com.autoflex.product_stock.dtos;

public record ProductMaterialDTO(
        String codeProduct,
        String codeMaterial,
        Integer necessaryQuantity
) {
}
