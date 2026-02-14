package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.product.ProductDTO;
import com.autoflex.product_stock.dtos.product.ProductMapper;
import com.autoflex.product_stock.dtos.product.ProductionSuggestionDTO;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.model.Product;
import com.autoflex.product_stock.model.ProductMaterial;
import com.autoflex.product_stock.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Test
    void shouldCreateProduct() {
        ProductDTO dto = new ProductDTO(
                1L,
                "PROD-400",
                "Produto Teste",
                new BigDecimal("10.00")
        );

        Product entity = new Product(
                "PROD-400",
                "Produto Teste",
                new BigDecimal("10.00")
        );

        when(productMapper.toEntity(dto)).thenReturn(entity);
        when(productRepository.save(entity)).thenReturn(entity);
        when(productMapper.toDTO(entity)).thenReturn(dto);

        ProductDTO result = productService.create(dto);
        assertEquals("PROD-400", result.code());

    }

    @Test
    void shouldSuggestProductionWhenStockIsAvailable() {
        Material material = new Material("MAT-1","Madeira", 10);
        ReflectionTestUtils.setField(material, "id", 1L);

        Product product = new Product("PROD-1", "Mesa", new BigDecimal("100"));

        ProductMaterial pm = new ProductMaterial(product, material, 2);

        product.setMaterials(Set.of(pm));

        when(productRepository.findAll(any(Sort.class))).thenReturn(List.of(product));

        ProductionSuggestionDTO result = productService.suggestProduction();

        assertEquals(5, result.producaoTotal());
        assertEquals(new BigDecimal("500"), result.valorTotal());
        assertTrue(result.productName().contains("Mesa"));
    }

}