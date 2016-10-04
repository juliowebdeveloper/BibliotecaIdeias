package br.com.bibideais.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Entidade implements Serializable, Comparable<Entidade>{

	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntidade;
	
	private String nome;
	
	private String endereco;
	
	@Column(length = 10)
	private String numero;
	
	private String telefone;
	
	private String email1;
	
	private String email2;
	
	private String email3;
	
	private String email4;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean isAssociada;
	

	
	private String complemento;

	
	private String bairro;
	
	private String cep;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	
	
	
	public Entidade (){}

	public int getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(int idEntidade) {
		this.idEntidade = idEntidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getEmail4() {
		return email4;
	}

	public void setEmail4(String email4) {
		this.email4 = email4;
	}

	public boolean isAssociada() {
		return isAssociada;
	}

	public void setAssociada(boolean isAssociada) {
		this.isAssociada = isAssociada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Entidade(String nome, String endereco, String numero,
			String telefone, String email1, String email2, String email3,
			String email4, boolean isAssociada, String complemento,
			String bairro, String cep, Cidade cidade) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.telefone = telefone;
		this.email1 = email1;
		this.email2 = email2;
		this.email3 = email3;
		this.email4 = email4;
		this.isAssociada = isAssociada;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int compareTo(Entidade o) {
		return getNome().compareTo(o.getNome());
	}
	
	
	
}
