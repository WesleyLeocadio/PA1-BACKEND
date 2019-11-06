package com.pa1.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pa1.backend.domain.Espaco;

import java.util.List;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Integer> {

    @Query(value = "SELECT * FROM ESPACO WHERE ESPACO_LOCALIZACAO = ?1", nativeQuery = true)
    List<Espaco> findByLocalizacao(String local);

    @Query(value = "SELECT * FROM ESPACO WHERE ESPACO_DESABILITADO = FALSE", nativeQuery = true)
    List<Espaco> findByHabilitado();

    @Query(value = "SELECT * FROM ESPACO WHERE ESPACO_DESABILITADO = TRUE", nativeQuery = true)
    List<Espaco> findByDesabilitado();

}
