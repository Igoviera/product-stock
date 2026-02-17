package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.productMaterial.ProductMaterialDTO;
import com.autoflex.product_stock.dtos.productMaterial.ProductMaterialMapper;
import com.autoflex.product_stock.exception.RecordNotFoundException;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.repository.MaterialRepository;
import com.autoflex.product_stock.repository.ProductMaterialRepository;
import com.autoflex.product_stock.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public ProductMaterialDTO create(ProductMaterialDTO productMaterialDTO) {
        ProductMaterial productMaterial = productMaterialMapper.toEntity(productMaterialDTO);

        Product product = productRepository.findByCode(productMaterial.getProduct().getCode())
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado com o código: " + productMaterialDTO.codeProduct()));

        Material material = materialRepository.findByCode(productMaterial.getMaterial().getCode())
                .orElseThrow(() -> new RecordNotFoundException("Matérial não encontrado com o código: " + productMaterialDTO.codeMaterial()));


        ProductMaterial newProductMaterial = new ProductMaterial(
                product,
                material,
                productMaterial.getNecessaryQuantity()
        );

        ProductMaterial saved = productMaterialRepository.save(newProductMaterial);
        return productMaterialMapper.toDTO(saved);
    }

    public ProductMaterialDTO getById(@Valid Long compositionId) {
        ProductMaterial productMaterial = productMaterialRepository.findById(compositionId)
                .orElseThrow(() -> new RecordNotFoundException("Composição de produto não encontrada com id: " + compositionId));

        return  productMaterialMapper.toDTO(productMaterial);
    }

    public Set<ProductMaterialDTO> getAll() {
        return productMaterialRepository.findAll()
                .stream().map(productMaterial -> productMaterialMapper.toDTO(productMaterial))
                .collect(Collectors.toSet());
    }

    @Transactional
    public ProductMaterialDTO update(@Valid Long compositionId, ProductMaterialDTO productMaterialDTO) {
        ProductMaterial productMaterial = productMaterialRepository.findById(compositionId)
                .orElseThrow(() -> new RecordNotFoundException("Composição não encontrada"));

        Product product = productRepository.findByCode(productMaterialDTO.codeProduct())
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado"));

        Material material = materialRepository.findByCode(productMaterialDTO.codeMaterial())
                .orElseThrow(() -> new RecordNotFoundException("Material não encontrado"));

        productMaterial.update(product, material, productMaterialDTO.necessaryQuantity());

        return productMaterialMapper.toDTO(productMaterialRepository.save(productMaterial));
    }

    public void delete(@Valid Long compositionId) {
        ProductMaterial productMaterial = productMaterialRepository.findById(compositionId)
                .orElseThrow(() -> new RecordNotFoundException("Composição não encontrada com id: " + compositionId));
        productMaterialRepository.delete(productMaterial);
    }
}
