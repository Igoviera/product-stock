package com.autoflex.product_stock.dtos;

import com.autoflex.product_stock.model.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product){
        if (product == null) return null;

        return new ProductDTO(
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getMaterials().stream().collect(Collectors.toSet())
        );
    }
}
