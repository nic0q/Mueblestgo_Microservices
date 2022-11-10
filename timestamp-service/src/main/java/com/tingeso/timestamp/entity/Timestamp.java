package com.tingeso.timestamp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timestamp")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    private String rut_employee;
    private Date date;
    private Integer extra_hours;
    private Integer late_minutes;

    public Timestamp(String rut_employee, Date date, Integer extra_hours, Integer late_minutes) {
        this.rut_employee = rut_employee;
        this.date = date;
        this.extra_hours = extra_hours;
        this.late_minutes = late_minutes;
    }
}
