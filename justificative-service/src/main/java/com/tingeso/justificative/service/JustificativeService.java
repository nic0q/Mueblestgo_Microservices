package com.tingeso.justificative.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tingeso.justificative.entity.Justificative;
import com.tingeso.justificative.repository.JustificativeRepository;

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
    public List<Justificative> getByRut(String rut) {
        return justificativeRepository.getJustificativeByRut(rut);
    }
    public Justificative save(Justificative justificative) {
        Justificative justificativeNew = justificativeRepository.save(justificative);
        return justificativeNew;
    }
}
