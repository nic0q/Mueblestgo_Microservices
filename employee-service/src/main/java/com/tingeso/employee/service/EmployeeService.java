package com.tingeso.employee.service;

import com.tingeso.employee.entity.Employee;
import com.tingeso.employee.entity.ExtraHours;
import com.tingeso.employee.entity.Justificative;
import com.tingeso.employee.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

  @SuppressWarnings("unchecked")
  public List<Justificative> getJustificatives(String employeeId) {
    List<Justificative> justificatives = restTemplate.getForObject(
      "http://justificative-service/justificatives/byemployee/" + employeeId,
      List.class
    );
    return justificatives;
  }

  @SuppressWarnings("unchecked")
  public List<ExtraHours> getExtraHours(String employeeId) {
    List<ExtraHours> extra_hours = restTemplate.getForObject(
      "http://extra-hours-service/extra-hours/byemployee/" + employeeId,
      List.class
    );
    return extra_hours;
  }
}
