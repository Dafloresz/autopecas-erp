package com.github.dafloresz.autopecas_erp.user.employee.dto;

import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;
import com.github.dafloresz.autopecas_erp.user.employee.Role;
import com.github.dafloresz.autopecas_erp.user.employee.Sector;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record EmployeeRequestDTO(@NotBlank String name,
                                 @NotBlank @CPF String cpf,
                                 @NotBlank String phone,
                                 @NotNull BigDecimal baseSalary,
                                 @NotBlank @Email String email,
                                 @NotBlank @Size(min = 6) String password,
                                 @NotNull Sector sector,
                                 @NotNull Role role,
                                 @NotNull AddressDTO address) {
}
