package com.github.dafloresz.autopecas_erp.category.mapper;

import com.github.dafloresz.autopecas_erp.category.CategoryProduct;
import com.github.dafloresz.autopecas_erp.category.dto.CategoryProductRequestDTO;
import com.github.dafloresz.autopecas_erp.category.dto.CategoryProductResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryProductMapper {
    public CategoryProductResponseDTO toCategoryResponseDTO(CategoryProduct categoryProduct) {
        return new CategoryProductResponseDTO(categoryProduct.getId(), categoryProduct.getName(), categoryProduct.getDescription());
    }

    public CategoryProduct toCategoryProduct(CategoryProductRequestDTO categoryRequestDTO) {
        return new CategoryProduct(categoryRequestDTO.name(), categoryRequestDTO.description());
    }

    public CategoryProduct toCategoryChange(CategoryProductRequestDTO categoryRequestDTO) {
        var categoryProduct = new CategoryProduct();

        if(categoryRequestDTO.name() != null){
            categoryProduct.setName(categoryRequestDTO.name());
        }

        if(categoryRequestDTO.description() != null){
            categoryProduct.setDescription(categoryRequestDTO.description());
        }
        return categoryProduct;
    }
}
