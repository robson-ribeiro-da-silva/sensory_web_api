package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.repository.AnaliseSensorialRepository;


@Service
public class AnaliseSensorialService {
	
	@Autowired
	private AnaliseSensorialRepository repository;
	
	public List<AnaliseSensorial> findAll() {
		return repository.findAll();
	}
	
	public AnaliseSensorial findOne(Long id) {
		return repository.getOne(id);
	}
	
	public AnaliseSensorial save(AnaliseSensorial analisesensorial) {
		return repository.saveAndFlush(analisesensorial);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public AnaliseSensorial findByIdAnalise(Long id){
		return repository.findByIdAnalise(id);
		
	}
	
	public AnaliseSensorial findByIdEscala(Long id){
		return repository.findByIdEscala(id);
	}

}
