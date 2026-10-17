package com.github.dafloresz.autopecas_erp.user.employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    Employee update(Long id, Employee employee);
    Employee findById(Long id);

    void activateEmployee(Long id);
    void deactivateEmployee(Long id);

    List<Employee> findAll(Boolean active);
    List<Employee> findAllBySector(Sector sector);
    List<Employee> findByName(String name);
    List<Employee> findByAdmissionDateBetween(LocalDate startDate, LocalDate endDate);

}
