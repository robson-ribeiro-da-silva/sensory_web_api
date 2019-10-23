package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Amostra;
import br.edu.ifrn.projetosensoryweb.repository.AmostraRepository;


@Service
public class AmostraService {
	
	@Autowired
	private AmostraRepository repository;
	
	public List<Amostra> findAll() {
		return repository.findAll();
	}
	
	public Amostra findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Amostra save(Amostra amostra) {
		return repository.saveAndFlush(amostra);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Amostra findByCodigo(int codigo){
		return repository.findByCodigo(codigo);
	}

}
