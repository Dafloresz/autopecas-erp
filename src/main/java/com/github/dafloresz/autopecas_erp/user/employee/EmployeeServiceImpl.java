package com.github.dafloresz.autopecas_erp.user.employee;

import com.github.dafloresz.autopecas_erp.exception.ResourceNotFoundException;
import com.github.dafloresz.autopecas_erp.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee save(Employee employee) {

        if(employee.getCpf() == null) {
            throw new IllegalArgumentException("Employee CPF must not be null");
        }

        if(employee.getEmail() == null) {
            throw new IllegalArgumentException("Employee email must not be null");
        }

        if(userRepository.existsByCpf(employee.getCpf())) {
            throw new EntityExistsException("Employee with CPF " + employee.getCpf() + " already exists");
        }

        if(userRepository.existsByEmail(employee.getEmail())) {
            throw new EntityExistsException("Employee email " + employee.getEmail() + " already exists");
        }

        if(employee.getUsername() != null) {
            if(userRepository.existsByUsername(employee.getUsername())) {
                throw new EntityExistsException("Employee username " + employee.getUsername() + " already exists");
            }
        }

        return  employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee change) {
        var employeeToUpdate = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id " + id + " not found"));

        if(change.getRole() != null) {
            if(!(change.getRole().equals(employeeToUpdate.getRole()))) {
                throw new ResourceNotFoundException("Employee role can not be changed");
            }
        }

        if(change.getCpf() != null) {
            if(userRepository.existsByCpfAndIdNot(change.getCpf(), id)) {
                throw new EntityExistsException("CPF: " + change.getCpf() + " already exists");

            } else {
                employeeToUpdate.setCpf(change.getCpf());
            }
        }

        if(change.getUsername() != null) {
            if(userRepository.existsByUsernameAndIdNot(change.getUsername(), id)) {
                throw new EntityExistsException("Employee username " + change.getUsername() + " already exists");

            } else {
                employeeToUpdate.setUsername(change.getUsername());
            }

        }

        if(change.getEmail() != null) {
            if(userRepository.existsByEmailAndIdNot(change.getEmail(), id)) {
                throw new EntityExistsException("Employee email " + change.getEmail() + " already exists");

            } else {
                employeeToUpdate.setEmail(change.getEmail());
            }

        }

        if(change.getName() != null) {
            employeeToUpdate.setName(change.getName());
        }

        if(change.getPassword() != null) {
            employeeToUpdate.setPassword(change.getPassword());
        }

        if(change.getPhone() != null) {
            employeeToUpdate.setPhone(change.getPhone());
        }

        if(change.getBaseSalary() != null) {
            employeeToUpdate.setBaseSalary(change.getBaseSalary());
        }

        if(change.getSector() != null) {
            employeeToUpdate.setSector(change.getSector());
        }


        if(change.getAddress() != null) {
            employeeToUpdate.setAddress(change.getAddress());
        }

        return employeeRepository.save(employeeToUpdate);
    }


    @Override
    public Employee findById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Employee id must not be null");
        }

        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " does not exist"));


    }

    @Override
    public void activateEmployee(Long id) {
        var employeeToActivate = employeeRepository
                .findById(id).orElseThrow(()  -> new ResourceNotFoundException("Employee with id " + id + " does not exist"));

        employeeToActivate.setActive(true);
        employeeRepository.save(employeeToActivate);

    }

    @Override
    public void deactivateEmployee(Long id) {
        var employeeToDeactivate = employeeRepository
                .findById(id).orElseThrow(() -> new  ResourceNotFoundException("Employee with id " + id + " does not exist"));

        employeeToDeactivate.setActive(false);
        employeeRepository.save(employeeToDeactivate);
    }

    @Override
    public List<Employee> findAll(Boolean active) {
        List<Employee> employees;

        if(active != null) {
            employees = employeeRepository.findAllByActive(active);

        } else {
            employees = employeeRepository.findAll();
        }

        return employees;

    }

    @Override
    public List<Employee> findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Employee name must not be null");
        }

        List<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(name);

        if(employees.isEmpty()){
            throw new ResourceNotFoundException("Employee with name " + name + " does not exist");
        }

        return employees;
    }

    @Override
    public List<Employee> findAllBySector(Sector sector) {
        if(sector == null) {
            throw new IllegalArgumentException("Employee sector must not be null");
        }

        List<Employee> employees = employeeRepository.findAllBySector(sector);

        if(employees.isEmpty()){
            throw new ResourceNotFoundException("No employees found in sector: " + sector.name());
        }

        return employees;
    }

    @Override
    public List<Employee> findByAdmissionDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Employee admission date must not be null");
        }

        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Employee admission start date must not be after end date");
        }

        List<Employee> employees = employeeRepository.findByAdmissionDateBetween(startDate, endDate);
        if(employees.isEmpty()){
            throw new ResourceNotFoundException("Employee with admission date " + startDate + " does not exist");
        }

        return employees;
    }

}
