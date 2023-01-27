package com.capgemini.start.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.start.api.dto.input.GeneroInputDTO;
import com.capgemini.start.api.dto.output.GeneroDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Genero", description = "Gênero de títulos")
public interface GeneroResource {
	
	@Operation(summary = "Consulta um Gênero por id")
	@GetMapping(value = "/{id}")
	ResponseEntity<GeneroDTO> findById(@PathVariable Long id);
	
	@Operation(summary = "Lista todos os gêneros")
	@GetMapping()
	ResponseEntity<List<GeneroDTO>> findAll();
	
	@Operation(summary = "Insere um gênero")
	@PostMapping()
	ResponseEntity<GeneroDTO> insert(@RequestBody @Valid GeneroInputDTO genero);
	
	@Operation(summary = "Atualiza um gênero")
	@PutMapping(value = "/{id}")
	ResponseEntity<GeneroDTO> update(@PathVariable Long id, @RequestBody @Valid GeneroInputDTO genero);
	
	@Operation(summary = "Exclui um gênero pelo id")
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);

}
