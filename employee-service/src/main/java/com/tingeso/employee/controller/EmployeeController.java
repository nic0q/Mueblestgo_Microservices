package com.tingeso.employee.controller;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        if(employees.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/{rut}")
    public ResponseEntity<Employee> getByRut(@PathVariable("rut") String rut) {
        Employee employee = employeeService.getEmployeeByRut(rut);
        if(employee == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }
    @PostMapping()
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee employeeNew = employeeService.save(employee);
        return ResponseEntity.ok(employeeNew);
    }
}
