package com.autoflex.product_stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


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
