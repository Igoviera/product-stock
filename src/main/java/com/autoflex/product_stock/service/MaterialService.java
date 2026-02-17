package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.material.MaterialDTO;
import com.autoflex.product_stock.dtos.material.MaterialMapper;
import com.autoflex.product_stock.exception.RecordNotFoundException;
import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    @Transactional
    public MaterialDTO create(MaterialDTO materialDTO) {
        return materialMapper.toDTO(materialRepository.save(materialMapper.toEntity(materialDTO)));
    }

    public MaterialDTO getById(Long materialId) {
        return materialMapper.toDTO(materialRepository.findById(materialId)
                .orElseThrow(() -> new RecordNotFoundException("Material não encontrado com id: " + materialId)));
    }

    public Set<MaterialDTO> getAll() {
        return materialRepository.findAll().stream()
                .map(material -> materialMapper.toDTO(material))
                .collect(Collectors.toSet());
    }

    @Transactional
    public void delete(@Valid Long materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RecordNotFoundException("Material não encontrado com id: " + materialId));
        materialRepository.delete(material);
    }

    @Transactional
    public MaterialDTO update(@Valid Long materialId, MaterialDTO materialDTO) {
        Material materialFound = materialRepository.findById(materialId)
                .orElseThrow(() -> new RecordNotFoundException("Material não encontrado com id: " + materialId));

        materialFound.update(
                materialDTO.name(),
                materialDTO.code(),
                materialDTO.stockQuantity()
        );

        return materialMapper.toDTO(materialRepository.save(materialFound));
    }
}
