package com.capgemini.start.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.start.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByIdNotAndEmailIgnoreCase(Long id, String email);

}