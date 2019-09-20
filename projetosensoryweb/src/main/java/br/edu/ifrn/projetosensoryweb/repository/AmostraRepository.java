package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.Amostra;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long>{ }
