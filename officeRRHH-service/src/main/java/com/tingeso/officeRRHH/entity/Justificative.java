package com.tingeso.officeRRHH.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Justificative {
  private Date date;
  private String rut_employee;
}