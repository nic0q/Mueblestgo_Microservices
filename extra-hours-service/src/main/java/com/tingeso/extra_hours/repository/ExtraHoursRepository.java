package com.tingeso.extra_hours.repository;

import com.tingeso.extra_hours.entity.ExtraHours;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraHoursRepository
  extends JpaRepository<ExtraHours, Integer> {
  @Query(
    value = "SELECT * FROM extra_hours WHERE rut_employee = :rut AND `date` = :datee",
    nativeQuery = true
  )
  ExtraHours getExtraHoursByRutDate(
    @Param("rut") String rut,
    @Param("datee") Date date
  );

  @Query(
    value = "SELECT * FROM extra_hours WHERE rut_employee = :rut",
    nativeQuery = true
  )
  List<ExtraHours> getExtraHoursByRut(@Param("rut") String rut);
}
