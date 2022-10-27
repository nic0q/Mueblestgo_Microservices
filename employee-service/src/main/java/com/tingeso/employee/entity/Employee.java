package com.tingeso.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
    @Id
    @Getter @Setter private String rut;
    @Getter @Setter private String name;
    @Getter @Setter private String last_name;
    @Getter @Setter private String category;
    @Getter @Setter private String birth_date;
    @Getter @Setter private String entry_date;
}
