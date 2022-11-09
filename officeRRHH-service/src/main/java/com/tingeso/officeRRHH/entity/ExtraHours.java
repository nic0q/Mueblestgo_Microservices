package com.tingeso.officeRRHH.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraHours {
    private Date date;
    private Integer n_hours;
    private String rut_employee;
}