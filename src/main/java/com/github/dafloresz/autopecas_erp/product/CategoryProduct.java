package com.github.dafloresz.autopecas_erp.product;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_category_product")
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 300)
    private String description;

    @OneToMany(mappedBy = "category")
    List<Product> products;


    public CategoryProduct() {
    }

    public CategoryProduct(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public CategoryProduct(String nome, List<Product> products, String description) {
        this.name = nome;
        this.products = products;
        this.description = description;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }
}
