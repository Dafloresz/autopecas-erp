package com.github.dafloresz.autopecas_erp.category;

import java.util.List;
import java.util.Optional;

public interface CategoryProductService {

    CategoryProduct findById(Long id);
    void deleteById(Long id);
    Optional<CategoryProduct> findByName(String name);
    CategoryProduct save(CategoryProduct categoryProduct);
    CategoryProduct update(Long id, CategoryProduct categoryProduct);
    CategoryProduct patch(Long id, CategoryProduct categoryProduct);
    List<CategoryProduct> findAll();
}
