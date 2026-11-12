package com.github.dafloresz.autopecas_erp.user.employee;

import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeePatchDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeePublicResponseDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeeRequestDTO;
import com.github.dafloresz.autopecas_erp.user.employee.dto.EmployeeResponseDTO;
import com.github.dafloresz.autopecas_erp.user.employee.mapper.EmployeeMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }


    @PostMapping(value = "/api/employees")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO request)  {
        Employee employee = employeeMapper.toEmployee(request);
        employee = employeeService.save(employee);
        EmployeeResponseDTO response = employeeMapper.toEmployeeResponseDTO(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PatchMapping(value = "/api/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeePatchDTO patchDTO) {
        if ((patchDTO.role() != null || patchDTO.baseSalary() != null) && !isAdmin()) {
            throw new AccessDeniedException("Only ADMIN can change role or salary");
        }

        Employee employee = employeeMapper.toEmployeeChange(patchDTO);
        employee = employeeService.update(id, employee);
        EmployeeResponseDTO  response = employeeMapper.toEmployeeResponseDTO(employee);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @PatchMapping(value = "/api/employees/{id}/activate")
    public ResponseEntity<Void> activateEmployee(@PathVariable Long id) {
        employeeService.activateEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/api/employees/{id}/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable Long id) {
        employeeService.deactivateEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/api/employees/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id)  {
        Employee employee = employeeService.findById(id);
        EmployeeResponseDTO response = employeeMapper.toEmployeeResponseDTO(employee);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping(value = "/api/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> findAllEmployees(@RequestParam(required = false) Boolean active) {
        List<Employee> employeeList = employeeService.findAll(active);

        List<EmployeeResponseDTO> response = employeeList
                                    .stream()
                                    .map(employeeMapper::toEmployeeResponseDTO)
                                    .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/api/employees/search")
    public ResponseEntity<List<EmployeeResponseDTO>> findByNameOrSector(@RequestParam(required = false) String name,
                                                                        @RequestParam(required = false) Sector sector) {

        List<Employee> employeeList = employeeService.searchByNameOrSector(name, sector);

        List<EmployeeResponseDTO> response = employeeList.stream()
                                            .map(employeeMapper::toEmployeeResponseDTO)
                                            .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/api/employees/directory")
    public ResponseEntity<List<EmployeePublicResponseDTO>> getEmployeesActiveDirectory() {
        List<Employee> employees = employeeService.findAll(true);
        List<EmployeePublicResponseDTO> response = employees.stream()
                .map(employeeMapper::toEmployeePublicResponseDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

}
