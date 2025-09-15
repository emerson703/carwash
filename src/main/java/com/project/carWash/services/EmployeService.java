package com.project.carWash.services;

import com.project.carWash.entity.Employee;
import com.project.carWash.repository.EmployeRepository;
import com.project.carWash.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;
    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    // CREATE
    public Employee save(Employee employee) {
        return employeRepository.save(employee);
    }

    // READ all
    public List<Employee> findAll() {
        return employeRepository.findAll();
    }

    // READ by ID
    public Optional<Employee> findById(Long id) {
        return employeRepository.findById(id);
    }

    // DELETE
    public void delete(Long id) {
        employeRepository.deleteById(id);
    }
}
