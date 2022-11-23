package com.tingeso.officeRRHH.controller;

import com.tingeso.officeRRHH.entity.Salarie;
import com.tingeso.officeRRHH.service.OfficeRRHHService;

import java.io.IOException;
import java.text.ParseException;
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

  @GetMapping
  public ResponseEntity<List<Salarie>> getAll() {
    List<Salarie> Salarie = officeRRHHService.getAll();
    if (Salarie.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(Salarie);
  }
  @GetMapping("/calcular")
  public ResponseEntity <List<Salarie>> getSalaries() throws ParseException, IOException {
    return ResponseEntity.ok(officeRRHHService.get_salaries());
  }
  @GetMapping("/calcular/{rut}")
  public ResponseEntity <Salarie> getSalarie(@PathVariable("rut") String rut) throws ParseException, IOException {
    Salarie salarie = officeRRHHService.get_salarie(rut);
    if(salarie == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(salarie);
  }
  @GetMapping("/{rut}")
  public ResponseEntity<Salarie> getByRut(@PathVariable("rut") String rut) {
    Salarie salarie = officeRRHHService.getSalariebyRut(rut);
    if (salarie == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(salarie);
  }
}
