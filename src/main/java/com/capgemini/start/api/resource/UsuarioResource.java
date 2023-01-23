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

import com.capgemini.start.api.dto.input.UsuarioInputDTO;
import com.capgemini.start.api.dto.output.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario", description = "Usuários do sistema")
public interface UsuarioResource {
	
	@Operation(summary = "Consulta um Usuario por id")
	@GetMapping(value = "/{id}")
	ResponseEntity<UsuarioDTO> findById(@PathVariable Long id);
	
	@Operation(summary = "Lista todos os usuarios")
	@GetMapping()
	ResponseEntity<List<UsuarioDTO>> findAll();
	
	@Operation(summary = "Insere um usuario")
	@PostMapping()
	ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioInputDTO usuario);
	
	@Operation(summary = "Atualiza um usuario")
	@PutMapping(value = "/{id}")
	ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioInputDTO usuario);
	
	@Operation(summary = "Exclui um usuario pelo id")
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);

}
