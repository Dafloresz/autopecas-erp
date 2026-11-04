package com.github.dafloresz.autopecas_erp.user.dto;

public record AddressDTO(String zipCode,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         String number,
                         String complement) {
}
