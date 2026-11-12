package com.github.dafloresz.autopecas_erp.user.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByActive(Boolean active);
    List<Employee> findByAdmissionDateBetween(LocalDate startDate, LocalDate endDate);

    @Query(
            """
            SELECT em FROM Employee em
            WHERE (:name IS NULL OR LOWER (em.name) LIKE LOWER (CONCAT('%', :name, '%')))
            AND (:sector IS NULL OR em.sector = :sector)
            """)
    List<Employee> searchByNameOrSector(@Param("name") String name, @Param("sector") Sector sector);

}
