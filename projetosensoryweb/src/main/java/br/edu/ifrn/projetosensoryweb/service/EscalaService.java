package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Escala;
import br.edu.ifrn.projetosensoryweb.repository.EscalaRepository;



@Service
public class EscalaService {
	
	@Autowired
	private EscalaRepository repository;
	
	public List<Escala> findAll() {
		return repository.findAll();
	}
	
	public Escala findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Escala save(Escala escala) {
		return repository.saveAndFlush(escala);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	

}
