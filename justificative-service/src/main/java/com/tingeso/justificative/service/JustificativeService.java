package com.tingeso.justificative.service;

import com.tingeso.justificative.entity.Justificative;
import com.tingeso.justificative.repository.JustificativeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificativeService {

  @Autowired
  JustificativeRepository justificativeRepository;

  public List<Justificative> getAll() {
    return justificativeRepository.findAll();
  }

  public Justificative getJustificativeById(Integer id) {
    return justificativeRepository.findById(id).orElse(null);
  }
  public Date convertir_fecha_url(String fecha) throws ParseException {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = sdf1.parse(fecha);
    return new java.sql.Date(date.getTime());
  }
  public Justificative getJustificativeByRut(String rut, String date) throws ParseException {
    return justificativeRepository.getJustificative(rut, convertir_fecha_url(date));
  }

  public Justificative save(Justificative justificative) {
    Justificative justificativeNew = justificativeRepository.save(
      justificative
    );
    return justificativeNew;
  }
}
