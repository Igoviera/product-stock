package com.autoflex.product_stock.dtos;

import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import org.springframework.stereotype.Component;

@Component
public class ProductMaterialMapper {

   public ProductMaterialDTO toDTO(ProductMaterial productMaterial){
       if (productMaterial == null) return null;

       return new ProductMaterialDTO(
               productMaterial.getProduct().getCode(),
               productMaterial.getMaterial().getCode(),
               productMaterial.getNecessaryQuantity()
       );
   }

   public ProductMaterial toEntity(ProductMaterialDTO dto){
       if (dto == null) return null;

       Product product = new Product(dto.codeProduct());
       Material material = new Material(dto.codeMaterial());

       ProductMaterial productMaterial = new ProductMaterial();
       productMaterial.setProduct(product);
       productMaterial.setMaterial(material);
       productMaterial.setNecessaryQuantity(dto.necessaryQuantity());

       return productMaterial;
   }
}
