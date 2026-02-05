package com.autoflex.product_stock.dtos;

import java.math.BigDecimal;
import java.util.Map;

public record ProductionSuggestionDTO(
        Map<String, Integer> suggestedQuantities,
        BigDecimal totalEstimatedValue
) {
}
