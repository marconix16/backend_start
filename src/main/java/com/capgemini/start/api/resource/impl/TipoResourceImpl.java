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

import com.capgemini.start.api.dto.input.TipoInputDTO;
import com.capgemini.start.api.dto.output.TipoDTO;
import com.capgemini.start.api.mapper.TipoMapper;
import com.capgemini.start.api.resource.TipoResource;
import com.capgemini.start.domain.entity.Tipo;
import com.capgemini.start.domain.service.TipoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/tipos")
public class TipoResourceImpl implements TipoResource{
	
	private final TipoService service;
	
	@Autowired
	private TipoMapper mapper;
	
	@Override
	public ResponseEntity<TipoDTO> findById(Long id) {
		Tipo tipo = this.service.findById(id);
		TipoDTO dto = mapper.toDTO(tipo);
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<List<TipoDTO>> findAll() {
		return ResponseEntity.ok(
					this.service.findAll()
					.stream()
					.map(tipo -> mapper.toDTO(tipo))
					.collect(Collectors.toList())
				);
	}

	@Override
	public ResponseEntity<TipoDTO> insert(@RequestBody @Valid TipoInputDTO tipo) {
		Tipo entity = mapper.toEntity(tipo);
		Tipo createdEntity = this.service.insert(entity);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEntity.getId())
                .toUri();

		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location.toString()).body(mapper.toDTO(createdEntity));
		
	}

	@Override
	public ResponseEntity<TipoDTO> update(Long id, @Valid TipoInputDTO tipo) {
		Tipo toUpdate = this.service.findById(id);
		toUpdate.setDescricao(tipo.getDescricao());
		
		Tipo updated = this.service.update(toUpdate);
		return ResponseEntity.ok(mapper.toDTO(updated));
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}

	
	
}