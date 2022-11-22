import React from "react";
import SalaryRow from "../components/SalaryRow";
import axios from "axios";
import { useState, useEffect } from "react";

export default function Planilla() {
  const [sueldos, setSueldos] = useState([]);
  useEffect(() => {
    getSueldos();
  },);
  const calcular = async () => {
    axios.get("/officerrhh/calcular").then((response) => {
      getSueldos()
      return response.data;
    });
  }
  const getSueldos = async () => {
    axios.get("/salaries").then((response) => {
      console.log(response.data);
      setSueldos(response.data);
      return response.data;
    });
  }
  return (
    <div>

    <button class="btn btn-primary" onClick={()=>calcular()}>Calcular</button>
  <table className="tabla table table-dark text-center rounded">
    <thead>
      <tr>
          <th> RUT </th>
          <th> Nombre </th>
          <th> Apellido </th>
          <th> Categoria </th>
          <th> Años Servicio </th>
          <th> Sueldo Fijo </th>
          <th> Bonificación </th>
          <th> Pago Horas Extra </th>
          <th> Descuento </th>
          <th> Sueldo Bruto </th>
          <th> Cotización Previsional </th>
          <th> Cotización Salud </th>
          <th> Sueldo Final </th></tr>
      </thead>
      <tbody>
        {sueldos.map((sueldo) => <SalaryRow key={sueldo.id}
                                        rut={sueldo.rut_empleado}
                                        nombre={sueldo.nombre_empleado}
                                        apellido={sueldo.apellido_empleado}
                                        categoria={sueldo.categoria}
                                        años_servicio={sueldo.anios_servicio}
                                        sueldo_fijo={sueldo.sueldo_fijo}
                                        bonificacion={sueldo.bonificacion}
                                        pago_horas_extra={sueldo.pago_horas_extras}
                                        descuento={sueldo.descuento}
                                        sueldo_bruto={sueldo.sueldo_bruto}
                                        cotizacion_prev={sueldo.cotizacion_previsional}
                                        cotizacion_salud={sueldo.cotizacion_salud}
                                        sueldo_final={sueldo.sueldo_final}
                                        />)}
      </tbody>
    
  </table></div>);
}