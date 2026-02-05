package com.autoflex.product_stock.dtos;

import com.autoflex.product_stock.model.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public MaterialDTO toDTO(Material material){
        if (material == null) return null;

        return new MaterialDTO(
                material.getCode(),
                material.getName(),
                material.getStockQuantity()
        );
    }

    public Material toEntity(MaterialDTO dto){
        if (dto == null) return null;

        Material material = new Material();
        material.setCode(dto.code());
        material.setName(dto.name());
        material.setStockQuantity(dto.stockQuantity());

        return material;
    }
}
