package com.autoflex.product_stock.controller;

import com.autoflex.product_stock.dtos.material.MaterialDTO;
import com.autoflex.product_stock.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/materials")
@Tag(name = "Raw material", description = "Endpoints para gestão de matéria prima")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Cadastrar nova matária prima",
            description = "Cadastra um produto no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matária prima cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    public MaterialDTO create(@Valid @RequestBody MaterialDTO materialDTO){
       return materialService.create(materialDTO);
    }

    @GetMapping()
    @Operation(
            summary = "Listar matárias primas",
            description = "Retorna uma lista de matária prima."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de matária prima retornada com sucesso"),
    })
    public Set<MaterialDTO> getAll(){
        return materialService.getAll();
    }

    @GetMapping("/{materialId}")
    @Operation(
            summary = "Obter matária prima por ID",
            description = "Retorna os dados de um matária prima com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matária prima encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matária prima não encontrada"),
    })
    public MaterialDTO getById(@Valid @PathVariable("materialId") Long materialId){
        return materialService.getById(materialId);
    }

    @PutMapping("/{materialId}/update")
    @Operation(
            summary = "Atualizar matária prima por ID",
            description = "Atualiza os dados de um matária prima com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matária prima atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Matária prima não encontrada"),
    })
    public MaterialDTO update(@Valid @PathVariable("materialId") Long materialId, @RequestBody MaterialDTO materialDTO ){
        return materialService.update(materialId, materialDTO);
    }

    @DeleteMapping("/{materialId}")
    @Operation(
            summary = "Remover matária prima do sistema",
            description = "Realiza a exclusão da matária prima."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matária prima removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matária prima não encontrada"),
    })
    public void delete(@Valid @PathVariable("materialId") Long materialId){
        materialService.delete(materialId);
    }
}
