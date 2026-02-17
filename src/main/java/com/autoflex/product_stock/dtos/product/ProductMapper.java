package com.autoflex.product_stock.dtos.product;

import com.autoflex.product_stock.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getPrice()
        );
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

       return new Product(
                dto.code(),
                dto.name(),
                dto.price()
        );
    }
}
