package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Avaliador;
import br.edu.ifrn.projetosensoryweb.repository.AvaliadorRepository;

@Service
public class AvaliadorService {

	@Autowired
	private AvaliadorRepository repository;
	
	public List<Avaliador> findAll() {
		return repository.findAll();
	}
	
	public Avaliador findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Avaliador save(Avaliador avaliador) {
		return repository.saveAndFlush(avaliador);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Avaliador findByCpf(String cpf){
		return repository.findByCpf(cpf);
	}
}
