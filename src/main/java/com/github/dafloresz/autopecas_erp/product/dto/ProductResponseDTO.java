package com.github.dafloresz.autopecas_erp.product.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id, String name, BigDecimal price, Integer quantity, String categoryName) {
}
