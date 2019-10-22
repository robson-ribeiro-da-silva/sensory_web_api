package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Escala implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="subtipo_id")
	private Subtipo subtipo;

	@ManyToMany
	@JoinTable(name="escala_has_avaliacaohedonica", joinColumns=
	{@JoinColumn(name="escala_id")}, inverseJoinColumns=
	{@JoinColumn(name="avaliacaohedonica_id")})
	private List<AvaliacaoHedonica> avaliacaohedonica;
	
	@JsonIgnore
	@OneToOne(mappedBy="escala")
	private AnaliseSensorial analisesensorial;
	
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

	public List<AvaliacaoHedonica> getAvaliacaohedonica() {
		return avaliacaohedonica;
	}

	public void setAvaliacaohedonica(List<AvaliacaoHedonica> avaliacaohedonica) {
		this.avaliacaohedonica = avaliacaohedonica;
	}

	public AnaliseSensorial getAnalisesensorial() {
		return analisesensorial;
	}

	public void setAnalisesensorial(AnaliseSensorial analisesensorial) {
		this.analisesensorial = analisesensorial;
	}
	
	

}
