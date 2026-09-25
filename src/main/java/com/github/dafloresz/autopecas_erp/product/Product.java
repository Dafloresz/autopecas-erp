package com.github.dafloresz.autopecas_erp.product;

import com.github.dafloresz.autopecas_erp.category.CategoryProduct;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;
    private Integer quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryProduct category;

    public Product() {

    }

    public Product(String name, Integer quantity, BigDecimal price) {
        this.name = name;
        setQuantity(quantity);
        setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity == null){
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if(isNegative(quantity)){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("Price cannot be zero");
        }

        if (isNegative(price)){
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    public CategoryProduct getCategory() {
        return category;
    }

    public void setCategory(CategoryProduct category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product = " + "Id: " + id
                + "Name: " + name + '\n'
                + ", Quantity:" + quantity
                + ", price=" + price;
    }

    private <T extends  Number> boolean  isNegative (T num) {
        if (Objects.isNull(num)){
            throw new IllegalArgumentException("Number cannot be null");
        }

        return num.doubleValue() < 0;
    }
}
