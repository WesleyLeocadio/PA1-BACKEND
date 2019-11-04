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

    @Query(value = "SELECT * FROM RESERVA WHERE DATA_RESERVA_INICIO = ?1", nativeQuery = true)
    List<Reserva> findByDate(Date d);

    @Query(value = "SELECT * FROM RESERVA WHERE ESPACO_ID = ?1", nativeQuery = true)
    List<Reserva> findByEspaco(Espaco e);

    //    //rever essa pesquisa retornar todas reservas do espaço da reserva editada
    @Query(value = "SELECT * FROM RESERVA WHERE ESPACO_ID = ?1 AND DATA_RESERVA_INICIO = ?2 ", nativeQuery = true)
    List<Reserva> findByReserva(Integer idEspaco, Date d);

    @Query(value = "SELECT * FROM RESERVA WHERE APROVADA = TRUE ", nativeQuery = true)
    List<Reserva> findByAprovadas();

    @Query(value = "SELECT * FROM RESERVA WHERE APROVADA = FALSE", nativeQuery = true)
    List<Reserva> findByPendentes();

}