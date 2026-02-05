package com.autoflex.product_stock.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stockQuantity;

    @OneToMany(mappedBy = "material")
    private Set<ProductMaterial> products;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Set<ProductMaterial> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductMaterial> products) {
        this.products = products;
    }
}
