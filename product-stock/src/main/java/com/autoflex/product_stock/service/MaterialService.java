package com.autoflex.product_stock.service;

import com.autoflex.product_stock.dtos.MaterialDTO;
import com.autoflex.product_stock.dtos.MaterialMapper;
import com.autoflex.product_stock.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public MaterialDTO create(MaterialDTO materialDTO) {
       return materialMapper.toDTO(materialRepository.save(materialMapper.toEntity(materialDTO)));
    }
}
