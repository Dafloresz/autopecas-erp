package com.github.dafloresz.autopecas_erp.user.employee.dto;

import com.github.dafloresz.autopecas_erp.user.dto.AddressDTO;
import com.github.dafloresz.autopecas_erp.user.employee.Role;
import com.github.dafloresz.autopecas_erp.user.employee.Sector;
import jakarta.validation.constraints.Email;

import java.math.BigDecimal;

public record EmployeePatchDTO(String name,
                               String cpf,
                               String phone,
                               BigDecimal baseSalary,
                               @Email String email,
                               Sector sector,
                               Role role,
                               AddressDTO address) {
}
