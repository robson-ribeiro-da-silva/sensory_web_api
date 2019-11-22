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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="AVALIACAO_HEDONICA")
public class AvaliacaoHedonica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String pergunta;
	
	private StatusAvaliacaoHedonica status;
	
	@JsonIgnore
	@ManyToMany(mappedBy="avaliacaohedonica")
	private List<Escala> escala;
	
	@OneToMany(mappedBy="avaliacaohedonica")
	private List<RespostaHedonica> respostahedonica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public StatusAvaliacaoHedonica getStatus() {
		return status;
	}

	public void setStatus(StatusAvaliacaoHedonica status) {
		this.status = status;
	}

	public List<Escala> getEscala() {
		return escala;
	}

	public void setEscala(List<Escala> escala) {
		this.escala = escala;
	}

	public List<RespostaHedonica> getRespostahedonica() {
		return respostahedonica;
	}

	public void setRespostahedonica(List<RespostaHedonica> respostahedonica) {
		this.respostahedonica = respostahedonica;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
