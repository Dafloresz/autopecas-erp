package com.github.dafloresz.autopecas_erp.user.employee;

import com.github.dafloresz.autopecas_erp.user.Address;
import com.github.dafloresz.autopecas_erp.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "tb_employee")
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

    @Column(nullable = false)
    private LocalDate admissionDate;
    private boolean active;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal baseSalary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sector sector;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Employee() {
        this.active = true;
        this.admissionDate = LocalDate.now();
    }

    public Employee(String name, String username, String cpf, String password, String email, String phone, Address address,
                   BigDecimal baseSalary, Sector sector, Role role) {

        super(name, username, cpf, password, email, phone, address);
        this.baseSalary = baseSalary;
        this.sector = sector;
        this.role = role;
        this.active = true;
        this.admissionDate = LocalDate.now();
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee = " +
                "Nome:" + getName() + '\'' +
                ", baseSalary=" + baseSalary +
                ", Phone: " + getPhone() + '\'' +
                ", Address:" + getAddress() + '\'' +
                ", admissionDate=" + admissionDate +
                ", active=" + active +
                ", role=" + role;
    }

}
