package com.autoflex.product_stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
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

    @JsonIgnore
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private Set<ProductMaterial> products = new HashSet<>();

    protected Material() {
    }

    public Material(String code, String name, Integer stockQuantity) {
        this.code = code;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public Material(String code) {
        this.code = code;
    }

    public void update (String code, String name, Integer stockQuantity) {
        this.code = code;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Material{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(code, material.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
