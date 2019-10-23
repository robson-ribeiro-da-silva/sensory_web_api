package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Escala;

@Repository
public interface EscalaRepository extends  JpaRepository<Escala, Long>{

	
}
