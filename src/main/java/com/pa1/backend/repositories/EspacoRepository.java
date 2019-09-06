package com.pa1.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.pa1.backend.domain.Espaco;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Integer> {

}
