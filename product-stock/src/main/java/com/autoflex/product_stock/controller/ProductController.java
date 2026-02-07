package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.ProductDTO;
import com.autoflex.product_stock.dtos.ProductionSuggestionDTO;
import com.autoflex.product_stock.repository.ProductRepository;
import com.autoflex.product_stock.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product", description = "Endpoints para gestão de Produtos")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Cadastrar novo produto",
            description = "Cadastra um produto no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    public ProductDTO create(@Valid @RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @GetMapping()
    @Operation(
            summary = "Listar produtos",
            description = "Retorna uma lista de produtos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
    })
    public Set<ProductDTO> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    @Operation(
            summary = "Obter produto por ID",
            description = "Retorna os dados de um produto com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    public ProductDTO getById(@Valid @PathVariable("productId") Long productId){
        return productService.getById(productId);
    }

    @PutMapping("/{productId}/update")
    @Operation(
            summary = "Atualizar produto por ID",
            description = "Atualiza os dados de um produto com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    public ProductDTO update(@Valid @PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO ){
        return productService.update(productId, productDTO);
    }

    @DeleteMapping("/{productId}")
    @Operation(
            summary = "Remover produto do sistema",
            description = "Realiza a exclusão do produto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    public void delete(@Valid @PathVariable("productId") Long productId){
        productService.delete(productId);
    }

    @GetMapping("/suggest")
    @Operation(
            summary = "Produto sugerido pelo sistema",
            description = "O sistema sugere o produto de maior valor."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto sugerido com sucesso"),
    })
    public ProductionSuggestionDTO suggestProduction(){
        return productService.suggestProduction();
    }
}
