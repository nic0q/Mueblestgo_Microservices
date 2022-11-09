package com.tingeso.officeRRHH.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeStamp {
  private Integer id;
  private String rut_employee;
  private Date date;
  private Integer extra_hours;
  private Integer late_minutes;
}
