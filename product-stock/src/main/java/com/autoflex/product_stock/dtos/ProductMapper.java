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
                product.getPrice()
        );
    }

    public Product toEntity(ProductDTO dto){
        if (dto == null) return null;

        Product product = new Product();
        product.setCode(dto.code());
        product.setName(dto.name());
        product.setPrice(dto.price());

        return product;
    }
}
