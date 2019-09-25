package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Escala implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="subtipo_id")
	public Subtipo subtipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Subtipo getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(Subtipo subtipo) {
		this.subtipo = subtipo;
	}
	
	

}
