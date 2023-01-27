package com.capgemini.start.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.capgemini.start.domain.entity.Genero;
import com.capgemini.start.domain.repository.GeneroRepository;
import com.capgemini.start.domain.service.exceptions.ObjectAlreadyExistsException;
import com.capgemini.start.domain.service.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GeneroService extends AbstractService<Genero, Long> {
	
	private final GeneroRepository repository;
	
	@Override
	protected JpaRepository<Genero, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public List<Genero> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Genero findById(Long id){
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Gênero não encontrado"));
	}
	
	@Override
	public Genero insert(Genero genero) {
		if (this.repository.existsByDescricaoIgnoreCase(genero.getDescricao())){
			throw new ObjectAlreadyExistsException("Já existe um gênero com esta descrição.");
		}
		genero.setDataCriacao(new Date());
		return this.repository.save(genero);
	}
	
	@Override
	public Genero update(Genero genero) {
		if (this.repository.existsByIdNotAndDescricaoIgnoreCase(genero.getId(), genero.getDescricao())) {
			throw new ObjectAlreadyExistsException("Já existe outro gênero com esta descrição.");
		}
		genero.setDataAlteracao(new Date());
		return this.repository.save(genero);
	}

}