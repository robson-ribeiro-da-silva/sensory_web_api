package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RespostaHedonica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String resposta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="avaliacaohedonica_id")
	public AvaliacaoHedonica avaliacaohedonica;
	
	@ManyToOne
	@JoinColumn(name="amostra_id")
	public Amostra amostra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public AvaliacaoHedonica getAvaliacaohedonica() {
		return avaliacaohedonica;
	}

	public void setAvaliacaohedonica(AvaliacaoHedonica avaliacaohedonica) {
		this.avaliacaohedonica = avaliacaohedonica;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
