package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tipo implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy="tipo")
	private List<AnaliseSensorial> analisesensorial;
	
	@OneToMany(mappedBy="tipo")
	private List<Subtipo> subtipo;
	
	
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

	public List<AnaliseSensorial> getAnalisesensorial() {
		return analisesensorial;
	}

	public void setAnalisesensorial(List<AnaliseSensorial> analisesensorial) {
		this.analisesensorial = analisesensorial;
	}

	public List<Subtipo> getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(List<Subtipo> subtipo) {
		this.subtipo = subtipo;
	}
	
	
}
