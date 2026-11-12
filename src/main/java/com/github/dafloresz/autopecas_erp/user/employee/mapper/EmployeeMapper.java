package com.github.dafloresz.autopecas_erp.user.employee.mapper;

import com.github.dafloresz.autopecas_erp.user.employee.Employee;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeePatchDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeePublicResponseDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeeRequestDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeeResponseDTO;
import com.github.dafloresz.autopecas_erp.user.mapper.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private final AddressMapper addressMapper;

    public EmployeeMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        var addressDTO = employee.getAddress() != null ? addressMapper.toAddressDTO(employee.getAddress()) : null;

        return new EmployeeResponseDTO(employee.getId(),
                                        employee.getName(),
                                        employee.getCpf(),
                                        employee.getPhone(),
                                        employee.getBaseSalary(),
                                        employee.getEmail(),
                                        employee.getSector(),
                                        employee.getRole(),
                                        addressDTO);
    }

    public EmployeePublicResponseDTO toEmployeePublicResponseDTO(Employee employee) {
        return new EmployeePublicResponseDTO(employee.getId(),
                employee.getName(),
                employee.getPhone(),
                employee.getSector(),
                employee.getRole());
    }

    public Employee toEmployee(EmployeeRequestDTO request) {
        var address = addressMapper.toAddress(request.address());
        return new Employee(request.name(),
                            null,
                            request.cpf(),
                            request.password(),
                            request.email(),
                            request.phone(),
                            address,
                            request.baseSalary(),
                            request.sector(),
                            request.role());
    }

    public Employee toEmployeeChange(EmployeePatchDTO patchDTO) {
        Employee employee = new Employee();
        if (patchDTO.name() != null) {
            employee.setName(patchDTO.name());
        }

        if (patchDTO.cpf() != null) {
            employee.setCpf(patchDTO.cpf());
        }

        if (patchDTO.phone() != null) {
            employee.setPhone(patchDTO.phone());
        }

        if (patchDTO.baseSalary() != null) {
            employee.setBaseSalary(patchDTO.baseSalary());
        }

        if (patchDTO.email() != null) {
            employee.setEmail(patchDTO.email());
        }

        if (patchDTO.sector() != null) {
            employee.setSector(patchDTO.sector());
        }

        if (patchDTO.role() != null) {
            employee.setRole(patchDTO.role());
        }

        if (patchDTO.address() != null) {
            employee.setAddress(addressMapper.toAddress(patchDTO.address()));
        }

        return employee;

    }

}
