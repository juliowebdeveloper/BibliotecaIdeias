package br.com.bibideais.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Organex
 *
 */
@Entity
public class Pavilhao implements Serializable, Comparable<Pavilhao>{

	public Pavilhao(){}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPavilhao;
	
	@Column(length = 80)
	private String nome;
	
	private String endereco;
	
	@Column(length = 10)
	private String numero;
	

	private String complemento;

	
	private String bairro;
	
	private String cep;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="local")
	private List<Feira>feiras;
	
	
	private String telefone;
	
	private String email;
	
	private String nomeContato;
	
	private String cargo;
	
	private String site;

	public int getIdPavilhao() {
		return idPavilhao;
	}

	public void setIdPavilhao(int idPavilhao) {
		this.idPavilhao = idPavilhao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Feira> getFeiras() {
		return feiras;
	}

	public void setFeiras(List<Feira> feiras) {
		this.feiras = feiras;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Pavilhao(String nome, String endereco, String numero,
			String complemento, String bairro, String cep, Cidade cidade,
			List<Feira> feiras, String telefone, String email, String nomeContato, String cargo, String site) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.feiras = feiras;
		this.telefone = telefone;
		this.email = email;
		this.nomeContato = nomeContato;
		this.cargo = cargo;
		this.site = site;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	
	public Pavilhao (String nome){
		this.nome = nome;
	}

	@Override
	public int compareTo(Pavilhao o) {
		return getNome().compareTo(o.getNome());
	}

	
}
