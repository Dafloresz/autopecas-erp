package com.github.dafloresz.autopecas_erp.user.client.dto;

import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ClientPatchDTO(
        String name,
        String username,
        String cpf,
        @Email String email,
        String phone,
        @Size(min = 6) String password,
        AddressDTO address
) {}
