package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.productMaterial.ProductMaterialDTO;
import com.autoflex.product_stock.service.ProductMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/compositions")
@Tag(name = "Material Production", description = "Endpoints para Vincular matéria-prima ao produto")
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;

    public ProductMaterialController(ProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Vincular matéria-prima ao produto",
            description = "Envia os dados necessários para a produção de um produto utilizando uma matéria-prima específica."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vínculo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    public ProductMaterialDTO create(@Valid @RequestBody ProductMaterialDTO productMaterialDTO) {
        return productMaterialService.create(productMaterialDTO);
    }

    @GetMapping()
    @Operation(
            summary = "Listar composição de produto",
            description = "Retorna uma lista da composição de cada produto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de composição retornada com sucesso"),
    })
    public Set<ProductMaterialDTO> getAll(){
        return productMaterialService.getAll();
    }

    @GetMapping("/{compositionId}")
    @Operation(
            summary = "Obter composição por ID",
            description = "Retorna os dados da composição do produto com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Composição encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Composição não encontrado"),
    })
    public ProductMaterialDTO getById(@Valid @PathVariable("compositionId") Long compositionId){
        return productMaterialService.getById(compositionId);
    }

    @PutMapping("/{compositionId}/update")
    @Operation(
            summary = "Atualizar composição por ID",
            description = "Atualiza os dados de um composição com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Composição atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Composição não encontrado"),
    })
    public ProductMaterialDTO update(@Valid @PathVariable("compositionId") Long compositionId, @RequestBody ProductMaterialDTO productMaterialDTO ){
        return productMaterialService.update(compositionId, productMaterialDTO);
    }


    @DeleteMapping("/{compositionId}")
    @Operation(
            summary = "Remover a composição do sistema",
            description = "Realiza a exclusão da composição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Composição removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Composição não encontrado"),
    })
    public void delete(@Valid @PathVariable("compositionId") Long compositionId){
        productMaterialService.delete(compositionId);
    }
}
