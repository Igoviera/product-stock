package com.autoflex.product_stock.controller;


import com.autoflex.product_stock.dtos.ProductMaterialDTO;
import com.autoflex.product_stock.service.ProductMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
