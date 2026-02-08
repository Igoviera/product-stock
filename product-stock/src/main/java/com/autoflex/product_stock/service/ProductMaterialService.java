package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.ProductMaterialDTO;
import com.autoflex.product_stock.dtos.ProductMaterialMapper;
import com.autoflex.product_stock.exception.RecordNotFoundException;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.repository.MaterialRepository;
import com.autoflex.product_stock.repository.ProductMaterialRepository;
import com.autoflex.product_stock.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;
    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;
    private final ProductMaterialMapper productMaterialMapper;


    public ProductMaterialService(ProductMaterialRepository productMaterialRepository, ProductRepository productRepository, MaterialRepository materialRepository, ProductMaterialMapper productMaterialMapper) {
        this.productMaterialRepository = productMaterialRepository;
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
        this.productMaterialMapper = productMaterialMapper;
    }

    public ProductMaterialDTO create(ProductMaterialDTO productMaterialDTO) {
        ProductMaterial productMaterial = productMaterialMapper.toEntity(productMaterialDTO);

        Product product = productRepository.findByCode(productMaterial.getProduct().getCode())
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado com o código: " + productMaterialDTO.codeProduct()));

        Material material = materialRepository.findByCode(productMaterial.getMaterial().getCode())
                .orElseThrow(() -> new RecordNotFoundException("Matérial não encontrado com o código: " + productMaterialDTO.codeMaterial()));


        ProductMaterial newProductMaterial = new ProductMaterial();
        newProductMaterial.setProduct(product);
        newProductMaterial.setMaterial(material);
        newProductMaterial.setNecessaryQuantity(productMaterial.getNecessaryQuantity());

        ProductMaterial saved = productMaterialRepository.save(newProductMaterial);
        return productMaterialMapper.toDTO(saved);
    }
}
