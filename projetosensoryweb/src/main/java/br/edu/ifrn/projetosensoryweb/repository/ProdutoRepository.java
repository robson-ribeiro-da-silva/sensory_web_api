package br.edu.ifrn.projetosensoryweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;
import br.edu.ifrn.projetosensoryweb.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { 
	
	@Query("select p from Produto p where p.analisesensorial = ?1")
	public List<Produto> findByCodigoAnalise(AnaliseSensorial analise);
}
