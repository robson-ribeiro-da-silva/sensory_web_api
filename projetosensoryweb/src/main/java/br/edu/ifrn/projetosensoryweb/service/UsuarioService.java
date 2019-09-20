package br.edu.ifrn.projetosensoryweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.projetosensoryweb.model.Usuario;
import br.edu.ifrn.projetosensoryweb.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Usuario save(Usuario usuario) {
		return repository.saveAndFlush(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
