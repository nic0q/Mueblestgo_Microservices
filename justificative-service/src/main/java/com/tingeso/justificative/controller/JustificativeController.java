package com.tingeso.justificative.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingeso.justificative.entity.Justificative;
import com.tingeso.justificative.service.JustificativeService;

@RestController
@RequestMapping("/justificatives")
public class JustificativeController {
  @Autowired
    JustificativeService justificativeService;

    @GetMapping
    public ResponseEntity<List<Justificative>> getAll() {
        List<Justificative> Justificatives = justificativeService.getAll();
        if(Justificatives.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(Justificatives);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Justificative> getById(@PathVariable("id") Integer id) {
        Justificative Justificative = justificativeService.getJustificativeById(id);
        if(Justificative == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Justificative);
    }
    @GetMapping("/byemployee/{employeeId}")
    public ResponseEntity<List<Justificative>> getByStudentId(@PathVariable("employeeId") String employeeId) {
        List<Justificative> books = justificativeService.getByRut(employeeId);
        return ResponseEntity.ok(books);
    }
    @PostMapping()
    public ResponseEntity<Justificative> save(@RequestBody Justificative Justificative) {
        Justificative JustificativeNew = justificativeService.save(Justificative);
        return ResponseEntity.ok(JustificativeNew);
    }
}
