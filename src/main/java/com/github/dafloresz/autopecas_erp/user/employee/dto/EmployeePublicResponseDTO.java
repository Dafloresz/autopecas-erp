package com.github.dafloresz.autopecas_erp.user.employee.dto;

import com.github.dafloresz.autopecas_erp.user.employee.Role;
import com.github.dafloresz.autopecas_erp.user.employee.Sector;

public record EmployeePublicResponseDTO(Long id,
                                        String name,
                                        String phone,
                                        Sector sector,
                                        Role role) {
}
