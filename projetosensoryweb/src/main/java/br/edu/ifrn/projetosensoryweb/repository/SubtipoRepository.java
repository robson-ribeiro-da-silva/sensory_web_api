package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Subtipo;

@Repository
public interface SubtipoRepository extends JpaRepository<Subtipo, Long>{

}
