package com.pa1.backend.repositories;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pa1.backend.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	 @Query(value = "SELECT * FROM USUARIO ORDER BY nome WHERE TIPO_USUARIO = ?1", nativeQuery = true)
	    List<Usuario> findAllTipo(Integer d);
	 

	 
	 @Query(value = "SELECT * FROM USUARIO WHERE TIPO_USUARIO = ?1 OR TIPO_USUARIO=?2", nativeQuery = true)
	    List<Usuario> findAllT(Integer x,Integer y);
	 

	 @Transactional(readOnly = true)
	 Usuario findByEmail(String email);

}
