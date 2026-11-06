package com.github.dafloresz.autopecas_erp.user.client.dto;

import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientRequestDTO(@NotBlank String name,
                               String username,
                               String cpf,
                               @NotNull @Email String email,
                               String phone,
                               @NotBlank @Size(min = 6) String password,
                               AddressDTO address) {
}
