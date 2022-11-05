package com.tingeso.extra_hours.service;

import com.tingeso.extra_hours.entity.ExtraHours;
import com.tingeso.extra_hours.repository.ExtraHoursRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraHoursService {

  @Autowired
  ExtraHoursRepository extraHoursRepository;

  public List<ExtraHours> getAll() {
    return extraHoursRepository.findAll();
  }

  public ExtraHours getJustificativeById(Integer id) {
    return extraHoursRepository.findById(id).orElse(null);
  }

  public List<ExtraHours> getExtraHoursByRut(String rut) {
    return extraHoursRepository.getExtraHoursByRut(rut);
  }

  public ExtraHours save(ExtraHours justificative) {
    ExtraHours justificativeNew = extraHoursRepository.save(justificative);
    return justificativeNew;
  }
}
