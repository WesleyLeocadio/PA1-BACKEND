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

    @Query(value = "SELECT * FROM RESERVA WHERE ID = ?1", nativeQuery = true)
    List<Reserva> findByEspaco(Espaco e);

    @Query(value = "SELECT * FROM RESERVA WHERE ESPACO_ID = ?1 AND DATA_RESERVA = ?2 ", nativeQuery = true)
    List<Reserva> findByDateEspaco(Integer id, Date d);

    @Query(value = "SELECT * FROM RESERVA WHERE ID = ?1 AND DATA_RESERVA = ?2 ", nativeQuery = true)
    List<Reserva> findByReserva(Integer id, Date d);

    @Query(value = "SELECT * FROM RESERVA WHERE (APROVADA = TRUE  AND CANCELADA = FALSE) ORDER BY DATA_RESERVA", nativeQuery = true)
    List<Reserva> findByAprovadas();

    @Query(value = "SELECT * FROM RESERVA WHERE (APROVADA = FALSE AND CANCELADA = FALSE) ORDER BY DATA_RESERVA ", nativeQuery = true)
    List<Reserva> findByPendentes();

    @Query(value = "SELECT * FROM RESERVA WHERE CANCELADA = TRUE ", nativeQuery = true)
    List<Reserva> findByCanceladas();

}