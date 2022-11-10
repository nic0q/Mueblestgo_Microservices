import React from "react";
export default function SalaryRow({rut, nombre, apellido, categoria, años_servicio, sueldo_fijo, bonificacion, pago_horas_extra, descuento, sueldo_bruto, cotizacion_prev, cotizacion_salud, sueldo_final}){   
  return  <tr>
            <td><span>{rut}</span></td>
            <td><span>{nombre}</span></td>
            <td><span>{apellido}</span></td>
            <td><span>{categoria}</span></td>
            <td><span>{años_servicio}</span></td>
            <td><span>{sueldo_fijo}</span></td>
            <td><span>{bonificacion}</span></td>
            <td><span>{pago_horas_extra}</span></td>
            <td><span>{descuento}</span></td>
            <td><span>{sueldo_bruto}</span></td>
            <td><span>{cotizacion_prev}</span></td>
            <td><span>{cotizacion_salud}</span></td>
            <td><span>{sueldo_final}</span></td>
            </tr>
}