package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.product.ProductDTO;
import com.autoflex.product_stock.dtos.product.ProductMapper;
import com.autoflex.product_stock.dtos.product.ProductionSuggestionDTO;
import com.autoflex.product_stock.exception.RecordNotFoundException;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.repository.MaterialRepository;
import com.autoflex.product_stock.repository.ProductMaterialRepository;
import com.autoflex.product_stock.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;
    private final ProductMaterialRepository productMaterialRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, MaterialRepository materialRepository, ProductMaterialRepository productMaterialRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
        this.productMaterialRepository = productMaterialRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        return productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO getById(Long productId) {
        return productMapper.toDTO(productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado com id: " + productId)));
    }

    public Set<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(product -> productMapper.toDTO(product))
                .collect(Collectors.toSet());
    }

    public ProductionSuggestionDTO suggestProduction() {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        Map<Long, Integer> availableStock = new HashMap<>();

        products.forEach(p -> p.getMaterials()
                .forEach(pm -> availableStock.put(
                                pm.getMaterial().getId(),
                                pm.getMaterial().getStockQuantity()
                        )
                )
        );

        Set<String> productNames = new LinkedHashSet<>();
        int totalProduced = 0;
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Product product : products) {
            int maxProduction = product.getMaterials().stream()
                    .mapToInt(pm -> {
                        int stock = availableStock.get(pm.getMaterial().getId());
                        return stock / pm.getNecessaryQuantity();
                    })
                    .min()
                    .orElse(0);

            if (maxProduction <= 0) continue;

            for (ProductMaterial pm : product.getMaterials()) {
                Long materialId = pm.getMaterial().getId();
                int remaining = availableStock.get(materialId) - (pm.getNecessaryQuantity() * maxProduction);
                availableStock.put(materialId, remaining);
            }

            productNames.add(product.getName());
            totalProduced += maxProduction;
            totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(maxProduction)));
        }

        return new ProductionSuggestionDTO(
                productNames,
                totalProduced,
                totalValue
        );
    }

    @Transactional
    public void delete(@Valid Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado com id: " + productId));
        productRepository.delete(product);
    }

    @Transactional
    public ProductDTO update(@Valid Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Produto não encontrado com id: " + productId));

        product.update(
                productDTO.code(),
                productDTO.name(),
                productDTO.price()
        );

        return productMapper.toDTO(productRepository.save(product));
    }
}
