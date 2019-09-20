package br.edu.ifrn.projetosensoryweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.projetosensoryweb.model.AnaliseSensorial;

@Repository
public interface AnaliseSensorialRepository extends JpaRepository<AnaliseSensorial, Long>{ }
