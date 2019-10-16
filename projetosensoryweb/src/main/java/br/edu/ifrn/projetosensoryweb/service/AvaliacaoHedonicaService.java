package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;
import br.edu.ifrn.projetosensoryweb.repository.AvaliacaoHedonicaRepository;


@Service
public class AvaliacaoHedonicaService {

	@Autowired
	private AvaliacaoHedonicaRepository repository;
	
	public List<AvaliacaoHedonica> findAll() {
		return repository.findAll();
	}
	
	public AvaliacaoHedonica findOne(Long id) {
		return repository.getOne(id);
	}
	
	public AvaliacaoHedonica save(AvaliacaoHedonica avaliacaoHedonica) {
		return repository.saveAndFlush(avaliacaoHedonica);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
