package br.edu.ifrn.projetosensoryweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.AvaliacaoHedonica;

@Repository
public interface AvaliacaoHedonicaRepository extends JpaRepository<AvaliacaoHedonica, Long>{

	@Query(value = "SELECT A.* FROM avaliacao_hedonica A INNER JOIN ESCALA_HAS_AVALIACAOHEDONICA EHA ON(A.ID = EHA.AVALIACAOHEDONICA_ID) INNER JOIN ESCALA E ON(EHA.ESCALA_ID = E.ID) INNER JOIN ANALISE_SENSORIAL AN ON(E.ID = AN.ESCALA_ID) WHERE AN.ID = ?1 AND  A.PERGUNTA = ?2", nativeQuery = true)
	public AvaliacaoHedonica findByIdAnaliseAndPergunta(Long id, String pergunta);
	
	@Query("select a from AvaliacaoHedonica a where a.id = ?1")
	public AvaliacaoHedonica findByIdAvaliacao(Long id);
	
	
}
