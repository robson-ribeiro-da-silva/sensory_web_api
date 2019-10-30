package br.edu.ifrn.projetosensoryweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;

@Repository
public interface AvaliacaoHedonicaRepository extends JpaRepository<AvaliacaoHedonica, Long>{

	@Query(value = "select a.* from avaliacao_hedonica a inner join escala_has_avaliacaohedonica eha on(a.id = eha.avaliacaohedonica_id) inner join escala e on(eha.escala_id = e.id) inner join analise_sensorial an on(e.id = an.escala_id) where an.id = ?1 and  a.pergunta = ?2", nativeQuery = true)
	public AvaliacaoHedonica findByIdAnaliseAndPergunta(Long id, String pergunta);
	
	@Query("select a from AvaliacaoHedonica a where a.id = ?1")
	public AvaliacaoHedonica findByIdAvaliacao(Long id);
	
	
}
