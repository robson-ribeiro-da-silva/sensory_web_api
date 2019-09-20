package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Avaliador;

@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long>{ 
	
	@Query
	public Avaliador findByCpf(String cpf);
}
