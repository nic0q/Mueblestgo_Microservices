package com.tingeso.extra_hours.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extra_hours")
@Data
public class ExtraHours {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Getter
  @Setter
  private Date date;

  @Getter
  @Setter
  private Integer n_hours;

  @Getter
  @Setter
  private String rut_employee;

  public ExtraHours(Date date, String rut_employee, Integer n_hours) {
    this.date = date;
    this.rut_employee = rut_employee;
    this.n_hours = n_hours;
  }
}
