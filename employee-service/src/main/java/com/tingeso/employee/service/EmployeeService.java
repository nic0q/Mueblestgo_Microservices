package com.tingeso.employee.service;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.entity.Justificative;
import com.tingeso.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

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
    public List<Justificative> getJustificatives(String employeeId) {
        List<Justificative> books = restTemplate.getForObject("http://justificative-service/justificatives/byemployee/" + employeeId, List.class);
        return books;
    }
}
