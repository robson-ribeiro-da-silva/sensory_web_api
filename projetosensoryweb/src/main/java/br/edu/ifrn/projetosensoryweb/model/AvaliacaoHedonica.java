package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class AvaliacaoHedonica implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pergunta;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
