package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Tipo;
import br.edu.ifrn.projetosensoryweb.repository.TipoRepository;


@Service
public class TipoService {
	
	@Autowired
	private TipoRepository repository;
	
	public List<Tipo> findAll() {
		return repository.findAll();
	}
	
	public Tipo findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Tipo save(Tipo tipo) {
		return repository.saveAndFlush(tipo);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
