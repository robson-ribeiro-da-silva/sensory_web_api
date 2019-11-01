package br.edu.ifrn.projetosensoryweb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;

@Repository
public interface AnaliseSensorialRepository extends JpaRepository<AnaliseSensorial, Long>{ 
	

	@Query(value = "select a.* from analise_sensorial a where a.escala_id = ?1", nativeQuery = true)
	public AnaliseSensorial findByIdEscala(Long id);
	
	@Query("select a from AnaliseSensorial a where a.id = ?1")
	public AnaliseSensorial findByIdAnalise(Long id);
	
	@Query(value = "select a.* from analise_sensorial a where a.usuario_id = ?1", nativeQuery = true)
	public List<AnaliseSensorial> findByIdUsuario(Long id);
}
