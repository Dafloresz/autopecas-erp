package com.github.dafloresz.autopecas_erp.user.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findAllBySector(Sector sector);
    List<Employee> findAllByActive(Boolean active);
    List<Employee> findByAdmissionDateBetween(LocalDate startDate, LocalDate endDate);

}
