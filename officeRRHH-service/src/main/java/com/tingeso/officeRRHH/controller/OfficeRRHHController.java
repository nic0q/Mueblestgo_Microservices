package com.tingeso.officeRRHH.controller;

import com.tingeso.officeRRHH.entity.Employee;
import com.tingeso.officeRRHH.entity.ExtraHours;
import com.tingeso.officeRRHH.entity.Justificative;
import com.tingeso.officeRRHH.entity.TimeStamp;
import com.tingeso.officeRRHH.service.OfficeRRHHService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
  public ResponseEntity <Employee> getEmployee(@PathVariable("rut") String rut) {
    Employee employee = officeRRHHService.getEmployeeByRut(rut);
    if (employee == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(employee);
  }
  @GetMapping("/extra-hours/{rut}")
  public ResponseEntity <List<ExtraHours>> getExtraHours(@PathVariable("rut") String rut)throws IOException {
    List<ExtraHours> extraHours = officeRRHHService.get_extra_hours(rut);
    if (extraHours == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(extraHours);
  }
  @GetMapping("/justificatives/{rut}/{date}")
  public ResponseEntity <Justificative> getJustificatives(@PathVariable("rut") String rut, @PathVariable("date") String date) {
    Justificative justificative = officeRRHHService.get_justificative(rut, date);
    if (justificative == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(justificative);
  }
  @GetMapping("/timestamp/{rut}/{date}")
  public ResponseEntity <TimeStamp> getTResponseEntity(@PathVariable("rut") String rut, @PathVariable("date") String date) {
    TimeStamp timeStamp = officeRRHHService.get_dia_trabajado(rut, date);
    if (timeStamp == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(timeStamp);
  }
  @GetMapping("/timestamp/date")
  public Date getDate() throws ParseException {
    return officeRRHHService.get_start_date();
  }
  @GetMapping("/test")
  public void test() throws ParseException, IOException{
    officeRRHHService.calcular_sueldo_bruto("20457671-9");
  }
}
