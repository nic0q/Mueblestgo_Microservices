package com.tingeso.justificative.repository;

import com.tingeso.justificative.entity.Justificative;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificativeRepository
  extends JpaRepository<Justificative, Integer> {
  @Query(
    value = "SELECT * FROM justificative WHERE rut_employee = :rut AND `date` = :datee",
    nativeQuery = true
  )
  Justificative getJustificative(
    @Param("rut") String rut,
    @Param("datee") Date date
  );

  // @Query(
  //   value = "SELECT * FROM justificative WHERE rut_employee = :rut",
  //   nativeQuery = true
  // )
  // Justificative getJustificativeByRut(@Param("rut") String rut);
}
