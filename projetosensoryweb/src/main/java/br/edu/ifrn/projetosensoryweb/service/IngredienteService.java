package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Ingrediente;
import br.edu.ifrn.projetosensoryweb.repository.IngredienteRepository;


@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository repository;
	
	public List<Ingrediente> findAll() {
		return repository.findAll();
	}
	
	public Ingrediente findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Ingrediente save(Ingrediente ingrediente) {
		return repository.saveAndFlush(ingrediente);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
