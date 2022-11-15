package com.tingeso.salarie.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Salarie {
    private @Getter @Setter int id;
    private @Getter @Setter String rut_empleado;
    private @Getter @Setter String nombre_empleado;
    private @Getter @Setter String apellido_empleado;
    private @Getter @Setter String categoria;
    private @Getter @Setter Integer anios_servicio;
    private @Getter @Setter double sueldo_fijo;
    private @Getter @Setter double bonificacion;
    private @Getter @Setter double pago_horas_extras;
    private @Getter @Setter double descuento;
    private @Getter @Setter double sueldo_bruto;
    private @Getter @Setter double cotizacion_previsional;
    private @Getter @Setter double cotizacion_salud;
    private @Getter @Setter double sueldo_final;
}