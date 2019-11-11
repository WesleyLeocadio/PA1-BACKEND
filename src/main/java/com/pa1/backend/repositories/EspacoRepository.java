package com.pa1.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pa1.backend.domain.Espaco;

import java.util.List;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Integer> {

    @Query(value = "SELECT * FROM ESPACO WHERE LOCALIZACAO = ?1", nativeQuery = true)
    List<Espaco> findByLocalizacao(String local);

    @Query(value = "SELECT * FROM ESPACO WHERE DESABILITADO = FALSE", nativeQuery = true)
    List<Espaco> findByHabilitado();

    @Query(value = "SELECT * FROM ESPACO WHERE DESABILITADO = TRUE", nativeQuery = true)
    List<Espaco> findByDesabilitado();

    @Query(value = "SELECT * FROM ESPACO WHERE COMPUTADORES = TRUE", nativeQuery = true)
    List<Espaco> findByComputadores();

    @Query(value = "SELECT * FROM ESPACO WHERE COMPUTADORES = FALSE", nativeQuery = true)
    List<Espaco> findByNComputadores();

}
