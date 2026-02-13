package com.autoflex.product_stock.dtos.product;


import java.math.BigDecimal;
import java.util.Set;

public record ProductionSuggestionDTO(
        Set<String> productName,
        int producaoTotal,
        BigDecimal valorTotal
) {
}
