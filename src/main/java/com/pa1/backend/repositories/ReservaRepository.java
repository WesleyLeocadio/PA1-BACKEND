package com.pa1.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pa1.backend.domain.Espaco;
import com.pa1.backend.domain.Reserva;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query(value = "SELECT * FROM RESERVA WHERE DATA_RESERVA = ?1", nativeQuery = true)
    List<Reserva> findByDate(Date d);
}