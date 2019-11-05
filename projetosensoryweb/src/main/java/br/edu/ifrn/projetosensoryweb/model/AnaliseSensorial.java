package br.edu.ifrn.projetosensoryweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="ANALISE_SENSORIAL")
public class AnaliseSensorial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotNull
	@NotEmpty(message = "Descrição não pode ser vazio.")
	private String descricao;
	
	@NotNull
	@NotEmpty(message = "Local não pode ser vazio.")
	private String local;
	
	@NotNull
	@NotEmpty(message = "Sala não pode ser vazio.")
	private String sala;

	private String alergias; 
	
	@NotNull
	@NotEmpty(message = "Data não pode ser vazio.")
	private String data;
	
	@NotNull
	@NotEmpty(message = "Quantidade de amostras não pode ser vazio.")
	private int qtdAmostras;
	
	private int totalProdutos;
	
	
	@OneToMany(mappedBy="analisesensorial")
	private List<Produto> produtos;
	
	@OneToOne
	@JoinColumn(name="escala_id")  
	public Escala escala;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_id")
	public Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public int getQtdAmostras() {
		return qtdAmostras;
	}

	public void setQtdAmostras(int qtdAmostras) {
		this.qtdAmostras = qtdAmostras;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Escala getEscala() {
		return escala;
	}

	public void setEscala(Escala escala) {
		this.escala = escala;
	}
	
	
	
}
