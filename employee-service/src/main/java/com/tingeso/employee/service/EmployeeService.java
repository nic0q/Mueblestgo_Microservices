package com.tingeso.employee.service;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeByRut(String id) {
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee save(Employee employee) {
        Employee employeeNew = employeeRepository.save(employee);
        return employeeNew;
    }
}
