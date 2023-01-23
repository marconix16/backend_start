package com.capgemini.start.api.dto.input;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInputDTO {
	
	@NotEmpty
	@Length(max = 100)
	private String nome;
	
	@NotEmpty
	private String email;
	
}
