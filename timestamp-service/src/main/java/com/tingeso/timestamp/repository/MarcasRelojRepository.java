package com.tingeso.timestamp.repository;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.timestamp.entity.MarcasReloj;

@Repository
public interface MarcasRelojRepository extends JpaRepository<MarcasReloj, Integer>{
    @Query(value="SELECT `date` FROM worked_days LIMIT 1;", nativeQuery=true)
    Date getDate();
    @Query(value="SELECT * FROM worked_days WHERE rut_employee = :rut AND `date` = :datee", nativeQuery=true)
    MarcasReloj getWorkedDay(@Param("rut")String rut,@Param("datee") Date date);
}
