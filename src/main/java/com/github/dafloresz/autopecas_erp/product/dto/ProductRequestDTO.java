package com.github.dafloresz.autopecas_erp.product.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, BigDecimal price, Integer quantity, Long categoryID) {
}
