package com.tingeso.timestamp.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tingeso.timestamp.entity.Timestamp;
import com.tingeso.timestamp.repository.TimeStampRepository;

@Service
public class TimeStampService {

  @Autowired
  private TimeStampRepository timeStampRepository;

  private static final String ENTRY_TIME = "08:00";
  private static final String EXIT_TIME = "18:00";
  private static final String NOMBRE_TXT = "DATA.txt";
  private static final DateFormat hours_mins = new SimpleDateFormat("hh:mm");

  private final Logger logg = LoggerFactory.getLogger(TimeStampService.class);

  public String save(MultipartFile file) {
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(file.getOriginalFilename());
        Files.write(path, bytes);
        logg.info("Archivo guardado");
      } catch (IOException e) {
        logg.error("No se pudo guardar", e);
      }
    }
    return "Archivo guardado correctamente";
  }
  public Date get_start_date(){
    return timeStampRepository.getDate();
  }
  public Date convertir_fecha_url(String fecha) throws ParseException {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = sdf1.parse(fecha);
    return new java.sql.Date(date.getTime());
  }
  public Date convertir_fecha_txt(String fecha) throws ParseException {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = sdf1.parse(fecha);
    return new java.sql.Date(date.getTime());
  }
  public void insert_worked_day(
    String rut_empleado,
    String fecha,
    Integer horas_extra,
    Integer minutos_tarde
  )
    throws ParseException {
    Timestamp marcasReloj = new Timestamp(
      rut_empleado,
      convertir_fecha_txt(fecha),
      horas_extra,
      minutos_tarde
    );
    timeStampRepository.save(marcasReloj);
  }

  public Timestamp get_dia_trabajado(String rut_empleado, String date) throws ParseException {
    return timeStampRepository.getWorkedDay(rut_empleado, convertir_fecha_url(date));
  }

  public int getLateMinutes(String entry_t) throws ParseException {
    long horas_trabajadas =
      hours_mins.parse(entry_t).getTime() -
      hours_mins.parse(ENTRY_TIME).getTime();
    if (TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas) < 0) { // Si entro antes se toma como hora de entrada (8:00 am)
      return 0;
    }
    return (int) TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
  }

  public int extraHours(String exit_t) throws ParseException {
    java.util.Date salida_normal = hours_mins.parse(EXIT_TIME);
    java.util.Date salida = hours_mins.parse(exit_t);
    long horas_extra = salida.getTime() - salida_normal.getTime();
    if (horas_extra <= 0) {
      return 0;
    }
    return (int) TimeUnit.MILLISECONDS.toHours(horas_extra);
  }

  public boolean readFile() throws FileNotFoundException, ParseException {
    try {
      InputStream ins = new FileInputStream(NOMBRE_TXT);
      ArrayList<String> dias = new ArrayList<>();
      Map<String, ArrayList<String>> ruts_map = new HashMap<>();
      try (Scanner obj = new Scanner(ins)) {
        while (obj.hasNextLine()) {
          String[] presence_reg = obj.nextLine().split(";"); //splitting the line into an array of strings
          String date = presence_reg[0];
          String hora = presence_reg[1];
          String rut = presence_reg[2].replace(".", ""); // Rut position // Hour position // Date position
          if (!dias.contains(date)) {
            dias.add(date);
            ruts_map.clear(); // the hashmap is cleared every day
          }
          if (!ruts_map.containsKey(rut)) {
            ArrayList<String> temp = new ArrayList<>(); // temporal array to save entry and quit times of a employee by rut
            temp.add(hora); // add entry time
            ruts_map.put(rut, temp); // hashmap rut and entry time
          } else {
            ruts_map.get(rut).add(hora); // hashmap exit time
            insert_worked_day(
              rut,
              date,
              extraHours(ruts_map.get(rut).get(1)),
              getLateMinutes(ruts_map.get(rut).get(0))
            );
            System.out.println(
              "Rut: " +
              rut +
              " Fecha: " +
              date +
              " Horas extra: " +
              extraHours(ruts_map.get(rut).get(1)) +
              " Minutos tarde: " +
              getLateMinutes(ruts_map.get(rut).get(0))
            );
          }
        }
      }
      return true;
    } catch (FileNotFoundException e) {
      return false;
    }
  }
  public List<Timestamp> getAll() {
    return timeStampRepository.findAll();
  }
}
