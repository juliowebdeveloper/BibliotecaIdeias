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

@Entity
public class Organizadora implements Serializable, Comparable<Organizadora>{

	
	
	private static final long serialVersionUID = 1L;

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrganizadora;
	
	
	@Column(length = 100)
	private String razaoSocial;
	
	@Column(length = 100)
	private String nomeFantasia;
	

	@Column(length = 30)
	private String skype;
	
	@Column(length = 30)
	private String cargo;
	
	@Column
	private String nomeContato;
	
	@Column(length = 30)
	private String telefone;
	
	@Column(length = 30)
	private String celular;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	private String logourl;
	
	
	
	//feiras organizadas
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "organizadora", fetch = FetchType.LAZY)
	private List<Feira> feiras;
	
	
	private String endereco;
	
	
	
	@Column(length = 10)
	private String numero;
	
	private String complemento;

	
	private String bairro;
	
	private String cep;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	
	
	
	
	
	

	public int getIdOrganizadora() {
		return idOrganizadora;
	}

	public void setIdOrganizadora(int idOrganizadora) {
		this.idOrganizadora = idOrganizadora;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public List<Feira> getFeiras() {
		return feiras;
	}

	public void setFeiras(List<Feira> feiras) {
		this.feiras = feiras;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Organizadora(String razaoSocial, String nomeFantasia, String skype,
			String cargo, String nomeContato, String telefone, String celular,
			String email,  List<Feira> feiras,
			String endereco, String numero,String complemento, String bairro, String cep, Cidade cidade, String logourl) {
		super();
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.skype = skype;
		this.cargo = cargo;
		this.nomeContato = nomeContato;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.complemento = complemento;
		this.feiras = feiras;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.logourl = logourl;
	}
	public Organizadora(){}

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

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	@Override
	public int compareTo(Organizadora o) {
		return getRazaoSocial().toLowerCase().compareTo(o.getRazaoSocial().toLowerCase());
	}
}
