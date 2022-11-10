package com.tingeso.officeRRHH.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.tingeso.officeRRHH.entity.Employee;
import com.tingeso.officeRRHH.entity.ExtraHours;
import com.tingeso.officeRRHH.entity.Justificative;
import com.tingeso.officeRRHH.entity.Salarie;
import com.tingeso.officeRRHH.entity.TimeStamp;
import com.tingeso.officeRRHH.repository.OfficeRRHHRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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
  ObjectMapper objectMapper;
  
  @Autowired
  RestTemplate restTemplate;

  @Autowired
  OfficeRRHHRepository officeRRHHRepository;
  
  public List<Salarie> getAll() {
    return officeRRHHRepository.findAll();
  }

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

  public Date get_start_date() throws ParseException {
    Date date = restTemplate.getForObject("http://timestamp-service/timestamps/date", Date.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    System.out.println(sdf.format(date));
    return date;
  }

  public Employee getEmployeeByRut(String rut_empleado) {
    Employee employee = restTemplate.getForObject("http://employee-service/employees/" + rut_empleado,Employee.class);
    System.out.println(employee);
    return employee;
  }

  public List<Employee> getEmployees() throws RestClientException, IOException{
    List<Employee> employees = jsonArrayToList(restTemplate.getForObject("http://employee-service/employees/", String.class), Employee.class);
    return employees;
  }

  public TimeStamp get_dia_trabajado(String rut, String date){
    TimeStamp timeStamp = restTemplate.getForObject("http://timestamp-service/timestamps/byemployee/" + rut +"/"+ date,TimeStamp.class);
    System.out.println(timeStamp);
    return timeStamp;
  }

  public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    return objectMapper.readValue(json, listType);
  }

  public List<ExtraHours> get_extra_hours(String rut) throws IOException{
    List<ExtraHours> extra_hours = jsonArrayToList(restTemplate.getForObject("http://extra-hours-service/extra-hours/byemployee/" + rut,String.class), ExtraHours.class);
    return extra_hours;
  }

  public Justificative get_justificative(String rut, String date){
    Justificative justificatives = restTemplate.getForObject("http://justificative-service/justificatives/byemployee/" + rut +"/"+ date, Justificative.class);
    System.out.println(justificatives);
    return justificatives;
  }

  public double calcular_descuentos(String rut_empleado) throws ParseException{
    double sueldo_base = get_sueldo_base(getEmployeeByRut(rut_empleado).getCategory());
    double descuentos = 0;
    Calendar c = Calendar.getInstance(); // creo el calendario
    c.setTime(get_start_date()); // seteo a la fecha actual
    c.add(Calendar.DATE, 1); // le sumo un dia
    for (int d = 1; d <= c.getActualMaximum(Calendar.DAY_OF_MONTH); d++) {
      c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), d);
      if(!laboralDay(c.get(Calendar.DAY_OF_WEEK))) {
        continue;
      }
      System.out.println(dateFormatSQL.format(c.getTime()));
      TimeStamp day = get_dia_trabajado(rut_empleado, dateFormatSQL.format(c.getTime()));
      System.out.println("DIA");
      System.out.println(day);
      if(notGoToWork(day)){
        if(get_justificative(rut_empleado, dateFormatSQL.format(c.getTime())) == null){ // no tiene justificativo
          descuentos += sueldo_base * DESCUENTO_INASISTENCIA;
        }
      }
      else{
        descuentos += descuentos_tardanza(day.getLate_minutes(), sueldo_base);
      }
    }
    return descuentos;
  }

  public double calcular_sueldo_bruto(String rut_empleado) throws ParseException, IOException{
    Employee empleado = getEmployeeByRut(rut_empleado);
    String categoria = empleado.getCategory();
    String entry_date = empleado.getEntry_date();
    double sueldo_base = get_sueldo_base(categoria);
    int anios_servicio = calcular_anios_servicio(entry_date);
    double sueldo = ( sueldo_base + calcular_bonificaciones(anios_servicio , sueldo_base) + calcular_sueldo_horas_extra(categoria, get_extra_hours(rut_empleado)) - calcular_descuentos(rut_empleado));
    System.out.println(sueldo);
    if(sueldo < 0){
      return 0;
    }
    return (Math.round(sueldo*100.0)/100.0);
  }

  public double calcular_cotizacion_salud(double sueldo_bruto){
    return (Math.round((sueldo_bruto * COTIZACION_SALUD)*100.0)/100.0);
  }

  public double calcular_cotizacion_previsional(double sueldo_bruto){
    return (Math.round((sueldo_bruto * COTIZACION_PREVISIONAL)*100.0)/100.0);
  }

  public double calcular_sueldo_final(double sueldo_bruto){
    return sueldo_bruto - calcular_cotizacion_salud(sueldo_bruto) - calcular_cotizacion_previsional(sueldo_bruto);
  }

  public Salarie getSalariebyRut(String rut){
    return officeRRHHRepository.getSalarie(rut);
  }

  public Salarie get_salarie(String rut_empleado) throws ParseException, IOException{
    Salarie salarie = new Salarie();
    salarie.setRut_empleado(rut_empleado);
    salarie.setNombre_empleado(getEmployeeByRut(rut_empleado).getName());
    salarie.setApellido_empleado(getEmployeeByRut(rut_empleado).getLast_name());
    salarie.setCategoria(getEmployeeByRut(rut_empleado).getCategory());
    salarie.setAnios_servicio(calcular_anios_servicio(getEmployeeByRut(rut_empleado).getEntry_date()));
    salarie.setSueldo_fijo(get_sueldo_base(getEmployeeByRut(rut_empleado).getCategory()));
    salarie.setBonificacion(calcular_bonificaciones(salarie.getAnios_servicio(), salarie.getSueldo_fijo()));
    salarie.setPago_horas_extras(calcular_sueldo_horas_extra(salarie.getCategoria(), get_extra_hours(rut_empleado)));
    salarie.setDescuento(calcular_descuentos(rut_empleado));
    salarie.setSueldo_bruto(calcular_sueldo_bruto(rut_empleado));
    salarie.setCotizacion_previsional(calcular_cotizacion_previsional(salarie.getSueldo_bruto()));
    salarie.setCotizacion_salud(calcular_cotizacion_salud(salarie.getSueldo_bruto()));
    salarie.setSueldo_final(calcular_sueldo_final(salarie.getSueldo_bruto()));
    officeRRHHRepository.save(salarie);
    return salarie;
  }

  public List<Salarie> get_salaries() throws RestClientException, IOException{
    List<Salarie> salaries = new ArrayList<Salarie>();
    List<Employee> employees = getEmployees();
    for (Employee employee : employees) {
      try {
        salaries.add(get_salarie(employee.getRut()));
      } catch (ParseException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return salaries;
  }
}
