package com.tingeso.officeRRHH.controller;

import com.tingeso.officeRRHH.entity.Employee;
import com.tingeso.officeRRHH.service.OfficeRRHHService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/officeRRHH")
public class OfficeRRHHController {

  @Autowired
  OfficeRRHHService officeRRHHService;

  @GetMapping("/employees/{rut}")
  public ResponseEntity <Employee> getPets(@PathVariable("rut") String rut) {
    Employee employee = officeRRHHService.getEmployeeByRut(rut);
    if (employee == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(employee);
  }
}
