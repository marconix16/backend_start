package com.capgemini.start.api.resource.impl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.start.api.dto.input.UsuarioInputDTO;
import com.capgemini.start.api.dto.output.UsuarioDTO;
import com.capgemini.start.api.mapper.UsuarioMapper;
import com.capgemini.start.api.resource.UsuarioResource;
import com.capgemini.start.domain.entity.Usuario;
import com.capgemini.start.domain.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioResourceImpl implements UsuarioResource{
	
	private final UsuarioService service;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Override
	public ResponseEntity<UsuarioDTO> findById(Long id) {
		Usuario usuario = this.service.findById(id);
		UsuarioDTO dto = mapper.toDTO(usuario);
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		return ResponseEntity.ok(
					this.service.findAll()
					.stream()
					.map(usuario -> mapper.toDTO(usuario))
					.collect(Collectors.toList())
				);
	}

	@Override
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioInputDTO usuario) {
		Usuario entity = mapper.toEntity(usuario);
		Usuario createdEntity = this.service.insert(entity);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEntity.getId())
                .toUri();

		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location.toString()).body(mapper.toDTO(createdEntity));
		
	}

	@Override
	public ResponseEntity<UsuarioDTO> update(Long id, @Valid UsuarioInputDTO usuario) {
		Usuario toUpdate = this.service.findById(id);
		toUpdate.setNome(usuario.getNome());
		toUpdate.setEmail(usuario.getEmail());
		
		Usuario updated = this.service.update(toUpdate);
		return ResponseEntity.ok(mapper.toDTO(updated));
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}