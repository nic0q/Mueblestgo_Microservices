package com.tingeso.justificative.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.justificative.entity.Justificative;

@Repository
public interface JustificativeRepository extends JpaRepository<Justificative, Integer> {
  @Query(value="SELECT * FROM justificative WHERE rut_employee = :rut AND `date` = :datee", nativeQuery=true)
  Justificative getJustificativeByRutDate(@Param("rut")String rut,@Param("datee") Date date);
  @Query(value="SELECT * FROM justificative WHERE rut_employee = :rut", nativeQuery=true)
  List<Justificative> getJustificativeByRut(@Param("rut")String rut);
}
