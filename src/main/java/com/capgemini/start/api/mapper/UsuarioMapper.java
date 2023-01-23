package com.capgemini.start.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.capgemini.start.api.dto.input.UsuarioInputDTO;
import com.capgemini.start.api.dto.output.UsuarioDTO;
import com.capgemini.start.domain.entity.Usuario;

@Component
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	UsuarioDTO toDTO(Usuario entity);
	
	@Mapping(target = "id", ignore = true)
	Usuario toEntity(UsuarioInputDTO dto);

}