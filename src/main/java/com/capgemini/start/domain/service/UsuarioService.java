package com.capgemini.start.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.capgemini.start.domain.entity.Usuario;
import com.capgemini.start.domain.repository.UsuarioRepository;
import com.capgemini.start.domain.service.exceptions.ObjectAlreadyExistsException;

@Service
public class UsuarioService extends AbstractService<Usuario, Long>{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public Usuario insert(Usuario entity) {
		if (this.repository.existsByEmailIgnoreCase(entity.getEmail())) {
			throw new ObjectAlreadyExistsException("Já existe usuário com este e-mail.");
		}
		return super.insert(entity);
	}

}
