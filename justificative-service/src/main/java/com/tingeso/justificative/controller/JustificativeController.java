package com.tingeso.justificative.controller;

import com.tingeso.justificative.entity.Justificative;
import com.tingeso.justificative.service.JustificativeService;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/justificatives")
public class JustificativeController {

  @Autowired
  JustificativeService justificativeService;

  @GetMapping
  @RolesAllowed("admin")
  public ResponseEntity<List<Justificative>> getAll() {
    List<Justificative> Justificatives = justificativeService.getAll();
    if (Justificatives.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(Justificatives);
  }
  @PostMapping
  @RolesAllowed("admin")
  public ResponseEntity<Justificative> save(@RequestBody Justificative justificative) {
    Justificative JustificativeNew = justificativeService.save(justificative);
    return ResponseEntity.ok(JustificativeNew);
  }
  @GetMapping("/{id}")
  @RolesAllowed("admin")
  public ResponseEntity<Justificative> getById(@PathVariable("id") Integer id) {
    Justificative Justificative = justificativeService.getJustificativeById(id);
    if (Justificative == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(Justificative);
  }

  @GetMapping("/byemployee/{rut}/{date}")
  public ResponseEntity<Justificative> getByRut(@PathVariable("rut") String rut, @PathVariable("date") String date) throws ParseException {
    Justificative justificatives = justificativeService.getJustificativeByRut(rut,date);
    return ResponseEntity.ok(justificatives);
  }
}
