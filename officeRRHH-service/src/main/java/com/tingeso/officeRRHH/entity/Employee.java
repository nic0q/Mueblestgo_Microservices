package com.tingeso.officeRRHH.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  private String rut;
  private String name;
  private String last_name;
  private String category;
  private String birth_date;
  private String entry_date;
}