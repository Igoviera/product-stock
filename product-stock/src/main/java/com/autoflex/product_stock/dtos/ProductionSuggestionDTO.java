package com.autoflex.product_stock.dtos;


import com.autoflex.product_stock.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record ProductionSuggestionDTO(
        Set<String> productName,
        int producaoTotal,
        BigDecimal valorTotal
) {
}
