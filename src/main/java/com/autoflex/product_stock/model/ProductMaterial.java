package com.autoflex.product_stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
public class ProductMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Material material;

    @Column(nullable = false)
    private Integer necessaryQuantity;

    protected ProductMaterial() {
    }

    public ProductMaterial(Product product, Material material, Integer necessaryQuantity) {
        this.product = product;
        this.material = material;
        this.necessaryQuantity = necessaryQuantity;
    }

    public void update(Product product, Material material, Integer necessaryQuantity) {
        this.product = product;
        this.material = material;
        this.necessaryQuantity = necessaryQuantity;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Material getMaterial() {
        return material;
    }

    public Integer getNecessaryQuantity() {
        return necessaryQuantity;
    }

    public void setNecessaryQuantity(Integer necessaryQuantity) {
        this.necessaryQuantity = necessaryQuantity;
    }

}
