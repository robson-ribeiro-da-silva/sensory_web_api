package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Avaliador implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotNull
	@NotEmpty(message = "CPF não pode ser vazio.")
	private String cpf;
	
	@NotNull
	@NotEmpty(message = "Nome não pode ser vazio.")
	private String nome;
	
	@NotNull
	@NotEmpty(message = "Sexo não pode ser vazio.")
	private String sexo;
	
	@NotNull
	@NotEmpty(message = "Data Nascimento não pode ser vazio.")
	private String dataNascimento;
	
	private boolean fumante;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isFumante() {
		return fumante;
	}

	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
