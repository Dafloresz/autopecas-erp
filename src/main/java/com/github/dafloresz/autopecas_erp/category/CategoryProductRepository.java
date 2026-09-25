package com.github.dafloresz.autopecas_erp.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Long> {
    boolean existsByName(String name);
    Optional<CategoryProduct> findByName(String name);
}
