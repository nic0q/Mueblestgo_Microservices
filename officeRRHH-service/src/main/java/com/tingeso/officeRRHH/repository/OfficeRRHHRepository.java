package com.tingeso.officeRRHH.repository;

import com.tingeso.officeRRHH.entity.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRRHHRepository extends JpaRepository<Salarie, Integer> {
  @Query(
    value = "SELECT * FROM salarie WHERE rut_empleado = :rut",
    nativeQuery = true
  )
  Salarie getSalarie(@Param("rut") String rut);
}
