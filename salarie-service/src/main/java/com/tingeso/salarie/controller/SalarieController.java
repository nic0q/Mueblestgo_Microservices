package com.tingeso.salarie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tingeso.salarie.entity.Salarie;
import com.tingeso.salarie.service.SalarieService;

@RestController
@RequestMapping("/salaries")
public class SalarieController {

  @Autowired
  SalarieService salarieService;

  @GetMapping
  public ResponseEntity<List<Salarie>> getAll() throws ParseException, IOException{
    List<Salarie> Salarie = salarieService.getAll();
    if (Salarie.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(Salarie);
  }
}
