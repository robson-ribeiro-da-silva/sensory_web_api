package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.RespostaHedonica;
import br.edu.ifrn.projetosensoryweb.repository.RespostaHedonicaRepository;


@Service
public class RespostaHedonicaService {
	
	@Autowired
	private RespostaHedonicaRepository repository;
	
	public List<RespostaHedonica> findAll() {
		return repository.findAll();
	}
	
	public RespostaHedonica findOne(Long id) {
		return repository.getOne(id);
	}
	
	public RespostaHedonica save(RespostaHedonica respostaHedonica) {
		return repository.saveAndFlush(respostaHedonica);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
