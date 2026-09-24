package com.github.dafloresz.autopecas_erp.product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void deleteById(Long id);

    Product update(Long id, Product product);

    Product patch(Long id, Product product);

    Product findById(Long id);

    List<Product> findByName(String name);

    List<Product> findByCategoryName(String category);

    List<Product> findAll();
}