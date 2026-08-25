package com.github.dafloresz.autopecas_erp.product.mapper;

import com.github.dafloresz.autopecas_erp.category.CategoryProduct;
import com.github.dafloresz.autopecas_erp.product.Product;
import com.github.dafloresz.autopecas_erp.product.dto.ProductRequestDTO;
import com.github.dafloresz.autopecas_erp.product.dto.ProductResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO productResponseDTO(Product product) {
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        return  new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), categoryName);
    }

    public Product toProduct(ProductRequestDTO productRequestDTO, CategoryProduct category) {
        Product product = new Product(productRequestDTO.name(), productRequestDTO.quantity(), productRequestDTO.price());
        product.setCategory(category);
        return product;

    }
}
