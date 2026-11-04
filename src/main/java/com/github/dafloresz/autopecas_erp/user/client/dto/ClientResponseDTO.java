package com.github.dafloresz.autopecas_erp.user.client.dto;

import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;

public record ClientResponseDTO(Long id,
                                String username,
                                String name,
                                String email,
                                String phone,
                                AddressDTO address) {
}
