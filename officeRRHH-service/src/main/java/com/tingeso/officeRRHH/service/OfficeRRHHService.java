package com.tingeso.officeRRHH.service;

import com.tingeso.officeRRHH.entity.Employee;
import com.tingeso.officeRRHH.entity.ExtraHours;
import com.tingeso.officeRRHH.entity.Justificative;
import com.tingeso.officeRRHH.entity.TimeStamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OfficeRRHHService {
  private static final double DESCUENTO_TARDANZA_10MIN = 0.01;
  private static final double DESCUENTO_TARDANZA_25MIN = 0.03;
  private static final double DESCUENTO_TARDANZA_45MIN = 0.06;
  private static final double DESCUENTO_INASISTENCIA = 0.15;
  private static final double BONIFICACIONES25 = 0.17;
  private static final double BONIFICACIONES20 = 0.14;
  private static final double BONIFICACIONES15 = 0.11;
  private static final double BONIFICACIONES10 = 0.08;
  private static final double BONIFICACIONES5 = 0.05;
  private static final double HORA_EXTRA_A = 25000;
  private static final double HORA_EXTRA_B = 20000;
  private static final double HORA_EXTRA_C = 10000;
  private static final double SUELDO_A = 1700000;
  private static final double SUELDO_B = 1200000;
  private static final double SUELDO_C = 800000;
  private static final double COTIZACION_PREVISIONAL = 0.10;
  private static final double COTIZACION_SALUD = 0.08;
  private DateFormat dateFormaty = new SimpleDateFormat("yyyy/MM/dd");
  private DateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");

  @Autowired
  RestTemplate restTemplate;

  public double get_sueldo_base(String categoria) {
    double sueldo = 0;
    if (categoria.equals("A")) {
      sueldo = SUELDO_A;
    } else if (categoria.equals("B")) {
      sueldo = SUELDO_B;
    } else if (categoria.equals("C")) {
      sueldo = SUELDO_C;
    }
    return (Math.round(sueldo * 100.0) / 100.0);
  }

  public double valor_horas_extra(String categoria, Integer n_horas_extra) {
    double horas = 0;
    if (categoria.equals("A")) {
      horas = n_horas_extra * HORA_EXTRA_A;
    } else if (categoria.equals("B")) {
      horas = n_horas_extra * HORA_EXTRA_B;
    } else if (categoria.equals("C")) {
      horas = n_horas_extra * HORA_EXTRA_C;
    }
    return horas;
  }

  public double calcular_sueldo_horas_extra(
    String categoria,
    List<ExtraHours> horas_extra
  ) {
    int n_horas_extra = 0;
    if (horas_extra != null) {
      for (int i = 0; i < horas_extra.size(); i++) {
        n_horas_extra += horas_extra.get(i).getN_hours();
      }
    }
    return valor_horas_extra(categoria, n_horas_extra);
  }

  public int calcular_anios_servicio(String entry_date) throws ParseException {
    return (int) Math.floor(
      TimeUnit.MILLISECONDS.toDays(
        (
          dateFormatSQL.parse(java.time.LocalDate.now().toString()).getTime() -
          dateFormaty.parse(entry_date).getTime()
        )
      ) *
      0.00273785
    );
  }

  public double calcular_bonificaciones(
    int anios_servicio,
    double sueldo_base
  ) {
    if (anios_servicio >= 25) {
      return (Math.round((sueldo_base * BONIFICACIONES25) * 100.0) / 100.0);
    } else if (anios_servicio >= 20) {
      return (Math.round((sueldo_base * BONIFICACIONES20) * 100.0) / 100.0);
    } else if (anios_servicio >= 15) {
      return (Math.round((sueldo_base * BONIFICACIONES15) * 100.0) / 100.0);
    } else if (anios_servicio >= 10) {
      return (Math.round((sueldo_base * BONIFICACIONES10) * 100.0) / 100.0);
    } else if (anios_servicio >= 5) {
      return (Math.round((sueldo_base * BONIFICACIONES5) * 100.0) / 100.0);
    }
    return 0;
  }

  public double descuentos_tardanza(Integer minutos_tarde, double sueldo_base) {
    double descuento = 0;
    if (minutos_tarde > 45) {
      descuento = sueldo_base * DESCUENTO_TARDANZA_45MIN;
    } else if (minutos_tarde > 25) {
      descuento = sueldo_base * DESCUENTO_TARDANZA_25MIN;
    } else if (minutos_tarde > 10) {
      descuento = sueldo_base * DESCUENTO_TARDANZA_10MIN;
    }
    return descuento;
  }

  public boolean laboralDay(int dayOfWeek) throws ParseException {
    return !(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
  }

  public boolean notGoToWork(TimeStamp dia) {
    return (dia == null || dia.getLate_minutes() > 70);
  }

  public Employee getEmployeeByRut(String rut) {
    Employee pets = restTemplate.getForObject("http://employee-service/employees/" + rut,Employee.class);
    return pets;
  }
  //public double calcular_descuentos(String rut_empleado) throws ParseException{
  //  double sueldo_base = get_sueldo_base(getEmployeeByRut(rut_empleado).getCategory());
  //  double descuentos = 0;
  //  Calendar c = Calendar.getInstance(); // creo el calendario
  //  c.setTime(workedDaysService.obtener_fecha_inicio()); // seteo a la fecha actual
  //  for (int d = 1; d <= c.getActualMaximum(Calendar.DAY_OF_MONTH); d++) {
  //    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), d);
  //    if(!laboralDay(c.get(Calendar.DAY_OF_WEEK))) {
  //      continue;
  //    }
  //    TimeStamp day = workedDaysService.get_dia_trabajado(rut_empleado, dateFormaty.format(c.getTime()));
  //    if(notGoToWork(day)){
  //      if(justificativeService.searchJustificative(rut_empleado, dateFormaty.format(c.getTime())) == null){ // no tiene justificativo
  //        descuentos += sueldo_base * DESCUENTO_INASISTENCIA;
  //      }
  //    }
  //    else{
  //      descuentos += descuentos_tardanza(day.getLate_minutes(), sueldo_base);
  //    }
  //  }
  //  return descuentos;
  //}
  //public double calcular_sueldo_bruto(String rut_empleado) throws ParseException{
  //  Employee empleado = employeeService.getEmployeeByRut(rut_empleado);
  //  String categoria = empleado.getCategory();
  //  String entry_date = empleado.getEntry_date();
  //  double sueldo_base = get_sueldo_base(categoria);
  //  int anios_servicio = calcular_anios_servicio(entry_date);
  //  double sueldo = ( sueldo_base + calcular_bonificaciones(anios_servicio , sueldo_base) + calcular_sueldo_horas_extra(categoria, extraHoursService.get_extra_hours_efectivas(rut_empleado)) - calcular_descuentos(rut_empleado));
  //  if(sueldo < 0){
  //    return 0;
  //  }
  //  return (Math.round(sueldo*100.0)/100.0);
  //}
  // public double calcular_cotizacion_salud(double sueldo_bruto){
  //   return (Math.round((sueldo_bruto * COTIZACION_SALUD)*100.0)/100.0);
  // }
  // public double calcular_cotizacion_previsional(double sueldo_bruto){
  //   return (Math.round((sueldo_bruto * COTIZACION_PREVISIONAL)*100.0)/100.0);
  // }
  // public double calcular_sueldo_final(double sueldo_bruto){
  //   return sueldo_bruto - calcular_cotizacion_salud(sueldo_bruto) - calcular_cotizacion_previsional(sueldo_bruto);
  // }
}
