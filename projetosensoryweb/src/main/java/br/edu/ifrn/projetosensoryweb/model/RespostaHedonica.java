package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class RespostaHedonica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String resposta;

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
