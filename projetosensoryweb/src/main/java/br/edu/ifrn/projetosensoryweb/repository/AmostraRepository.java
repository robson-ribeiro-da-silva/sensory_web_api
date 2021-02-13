package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Amostra;
import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long>{
	
	@Query("select a from Amostra a where a.codigo = ?1")
	public Amostra findByCodigo(int codigo);
	
	//SELECT A.ID, A.CODIGO FROM AMOSTRA A INNER JOIN PRODUTO P ON A.PRODUTO_ID = P.ID WHERE P.ANALISESENSORIAL_ID = 4 AND A.CODIGO = 	1167
	@Query(value = "SELECT A.* FROM AMOSTRA A INNER JOIN PRODUTO P ON(A.PRODUTO_ID = P.ID) WHERE P.ANALISESENSORIAL_ID = ?1 AND A.CODIGO = ?2", nativeQuery = true)
	public Amostra findByIdAnaliseAndCodAmostra(Long id, int codigo);
}
