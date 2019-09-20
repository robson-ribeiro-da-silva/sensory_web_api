package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Produto;
import br.edu.ifrn.projetosensoryweb.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Produto save(Produto produto) {
		return repository.saveAndFlush(produto);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
