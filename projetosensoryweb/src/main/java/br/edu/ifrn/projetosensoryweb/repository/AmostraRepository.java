package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Amostra;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long>{
	
	@Query("select a from Amostra a where a.codigo = ?1")
	public Amostra findByCodigo(int codigo);
}
