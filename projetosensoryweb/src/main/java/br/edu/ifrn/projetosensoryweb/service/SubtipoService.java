package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Subtipo;
import br.edu.ifrn.projetosensoryweb.repository.SubtipoRepository;

@Service
public class SubtipoService {
	
	@Autowired
	private SubtipoRepository repository;
	
	public List<Subtipo> findAll() {
		return repository.findAll();
	}
	
	public Subtipo findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Subtipo save(Subtipo subtipo) {
		return repository.saveAndFlush(subtipo);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
