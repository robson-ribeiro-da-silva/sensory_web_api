package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Amostra implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private int codigo;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.ALL})
	private Produto produto;
	
	@JsonIgnore
	@OneToMany(mappedBy="amostra")
	private List<RespostaHedonica> respostahedonica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
