package com.tingeso.officeRRHH.entity;

import java.sql.Date;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConstructorBinding
public class ExtraHours {
    private Integer id;
    private Date date;
    private Integer n_hours;
    private String rut_employee;
}