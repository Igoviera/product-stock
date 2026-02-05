package com.autoflex.product_stock.service;

import com.autoflex.product_stock.model.Material;
import com.autoflex.product_stock.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Material create(Material material) {
       return materialRepository.save(material);
    }
}
